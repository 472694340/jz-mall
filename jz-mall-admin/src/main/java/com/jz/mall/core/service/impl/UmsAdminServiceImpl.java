package com.jz.mall.core.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jz.mall.core.domain.model.UmsAdmin;
import com.jz.mall.core.domain.model.UmsRole;
import com.jz.mall.core.mapper.UmsAdminMapper;
import com.jz.mall.core.domain.model.UmsPermission;
import com.jz.mall.core.service.UmsAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jz.mall.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author ShenLiang
 * @since 2023-02-21
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {
    private static final Logger log = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UmsAdminMapper umsAdminMapper;


    @Override
    public UmsAdmin getUserByName(String username) {
        UmsAdmin umsAdmin = umsAdminMapper.selectOne(new QueryWrapper<UmsAdmin>().eq("username", username));
        return umsAdmin;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        List<UmsPermission> permissions = umsAdminMapper.getPermissionList(adminId);
        return permissions;
    }

    //并将用户信息存入SpringSecurity中,下次登录在token未失效的情况下就不用再经过这里
    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //这里应该需要去对比一下,从数据库中查出来的密码和现在传过来的密码是否一致,相等,则登录成功,生成token返回,
            if (passwordEncoder.matches(password,userDetails.getPassword())){
                token = jwtUtil.generatorTokenByClaims(userDetails);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (AuthenticationException e){
            log.debug("登录异常", JSONUtil.parse(e.getMessage()));
        }
        return token;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        String encodedPassword = passwordEncoder.encode(umsAdminParam.getPassword());
        Date createTime = new Date();
        umsAdminParam.setCreateTime(createTime);
        umsAdminParam.setPassword(encodedPassword);
        int insert = umsAdminMapper.insert(umsAdminParam);
        if (insert == 1){
            return umsAdminParam;
        }
        return null;
    }

    @Override
    public List<UmsRole> getRoleList(Long id) {
        List<UmsRole> roles = umsAdminMapper.getRoleList(id);
        return roles;
    }
}
