package com.jz.mall.config;

import com.jz.mall.filter.AccessDeniedHandlerFilter;
import com.jz.mall.filter.AuthenticationEntryPointFilter;
import com.jz.mall.filter.JwtAuthenticationTokenFilter;
import com.jz.mall.generator.dto.AdminUserDetails;
import com.jz.mall.generator.model.UmsAdmin;
import com.jz.mall.generator.model.UmsPermission;
import com.jz.mall.generator.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 *  SpringSecurity配置类
 *    1.对静态资源放行
 *    2.对登录接口放行,对其他接口访问进行验证 ==>需要添加验证过滤器
 *    3.数据库连接,SpringSecurity 中需要注入用户信息 在UserDetails 接口中
 *    4.对密码进行加密
 *    5.对访问异常进行处理
 */

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    UmsAdminService umsAdminService;

    /**
     * 1.对静态资源进行放行(静态资源是和web浏览器有关系)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**","/swagger-resources/**",
                "/v2/api-docs/**");//ignoring()用来配置忽略掉的URL
    }

    /**
     *2. 对登录接口放行 和 HTTP请求有关系
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //使用JWT不用防范跨域攻击
        .sessionManagement()//不使用session,结束这一些配置,开始过滤配置
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
        .authorizeRequests()//开启需要放行的接口配置
                .antMatchers("/admin/login","/admin/register").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()//除去上面的放行,其他都需要进行验证
                .and()
        .headers()//禁用缓存
                .cacheControl()
                .disable()
                .and()
        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)//添加自定义JWT过滤器,验证token
        .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())//添加未登录结果返回 参数是AccessDeniedHandler accessDeniedHandler
        .authenticationEntryPoint(authenticationEntryPointFilter());//添加未验证结果返回  参数 AuthenticationEntryPoint authenticationEntryPoint

    }

    /**
     * 自定义JWT过滤器,验证token
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 3.连接数据库用户
     * @param auth  配置用户信息(注入SpringSecurity)
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(getUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 3.连接数据用户
     * 都需要有一个类继承SpringSecurity里的UserDetails,将查出来的信息封装注入,区别是,是否需要重写UserDetailsService里的 loadUserByUsername(String username)
     * @return
     */
    @Bean
    public UserDetailsService getUserDetailsService() {
        return username -> {
            UmsAdmin umsAdmin = umsAdminService.getUserByName(username);
            if (umsAdmin != null){
                //查询用户权限,然后交给SpringSecurity内置的UserDetails
                List<UmsPermission> permissions = umsAdminService.getPermissionList(umsAdmin.getId());
                return new AdminUserDetails(umsAdmin,permissions);
            }
                throw new UsernameNotFoundException("用户名/密码错误");
        };
    }

    /**
     * 4.对密码进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//通过这种加密方式,可以指定版本
    }

    /**
     * 5.对异常进行处理
     *
     */
    @Bean
    public AuthenticationEntryPointFilter authenticationEntryPointFilter(){
        return new AuthenticationEntryPointFilter();
    }

    @Bean
    public AccessDeniedHandlerFilter accessDeniedHandler(){
        return new AccessDeniedHandlerFilter();
    }

}
