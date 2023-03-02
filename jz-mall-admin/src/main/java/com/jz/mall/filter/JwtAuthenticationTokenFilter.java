package com.jz.mall.filter;

import com.jz.mall.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这里是不是一定只能继承OncePerRequestFilter 这个抽象类
 *  自定义JWT过滤器,验证token
 *   验证流程:
 *    1.用户登录,从请求头header中获取token信息,没有token,说明未登录,需要将用户信息,进行校验,无误后存入SpringSecurityContext中
 *    2.如果获取到了token,说明用户已登录,验证token是否失效,未失效,则刷新token时间,重置SpringSecurity中的用户信息
 *    3.失效了,重新登录,存入SpringSecurityContext中
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        String authHeader = req.getHeader(this.tokenHeader); //
        if (authHeader !=null && authHeader.startsWith(this.tokenHead)){//说明有token,需要进一步验证
            //验证步骤:从header中取出token值,看信息和SpringSecurity中保存的用户信息是否一致
            String authToken = authHeader.substring(this.tokenHead.length());
            String userNameFormToken = jwtUtil.parseUserNameFormToken(authToken);
            log.info("checking username:{}", userNameFormToken);
            //如果用户名不是空 并且权限为空,没验证过
            if (userNameFormToken != null && SecurityContextHolder.getContext().getAuthentication() == null){//如果从请求中的username不为空,且SpringSecurity里的用户信息为空,表示没有被验证过
                log.info("authenticated user:{}", userNameFormToken);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userNameFormToken);//通过将用户名信息和权限信息 存入SpringSecurity
                if (jwtUtil.verifyToken(authToken,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
            chain.doFilter(req,res);

    }
}
