package com.jz.mall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.PrintWriter;

/**
 *  SpringSecurity配置类
 *    1.对静态资源放行
 *    2.对登录接口放行
 *    3.对其他接口访问进行验证,需要添加验证过滤器
 *    4.自定义数据库连接
 *    5.对密码进行加密
 *    6.对访问异常进行处理
 */
/*
    @AliasFor("prefix")
    String value() default "";

    @AliasFor("value")
    String prefix() default "";
 */
@ConfigurationProperties(prefix="spring.security")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 1.对静态资源进行放行(静态资源是和web浏览器有关系)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");//ignoring()用来配置忽略掉的URL
    }



    /**
     *2. 对登录接口放行 和 HTTP请求有关系
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //a.江南一点雨
        http.authorizeRequests().anyRequest().authenticated()//authorizeRequests ---> (intercept-url)表示拦截的URL  //表示所有的URL都会被拦截
            .and()//and()表示结束这个标签,开始下一轮配置
            .formLogin()//指定登录成功后的跳转页面,不指定的话就是默认的index,没有index页面就会报404,这是前后端不分离的适配的
            .loginPage("/login.html").permitAll()  //表示让SpringSecurity去找一个login.html页面作为登录页面,同时因为放行了静态资源html,所以可以正常访问//这里的loginPage默认设置都在FormLoginConfigurer类中

                //前后端分离的配置 采用successHandler 和 failureHandler
                //successHandler 方法的参数是一个 AuthenticationSuccessHandler 对象，这个对象中我们要实现的方法是 onAuthenticationSuccess。  void onAuthenticationSuccess(HttpServletRequest var1, HttpServletResponse var2, Authentication var3) throws IOException, ServletException;
                //登录成功后,返回的是: {"password":null,"username":"chenliang","authorities":[{"authority":"ROLE_admin"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
                .successHandler((request,response,authentication)->{
                    Object principal = authentication.getPrincipal();
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out  = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                })
                //failureHandler()的参数是:AuthenticationFailureHandler 对象  要实现的方法是:void onAuthenticationFailure(HttpServletRequest var1, HttpServletResponse var2, AuthenticationException var3) throws IOException, ServletException;
                //登录失败返回的信息是; "Bad credentials"
                .failureHandler((request,response,e)->{
                    response.setContentType("application/json;charset=utf-8");
                    String message = e.getMessage();
                    PrintWriter out  = response.getWriter();
                    //这里失败的异常可以进一步细化处理
//                    if (e instanceof RuntimeException){
//                        message = "运行时异常";
//                    }
                    out.write(new ObjectMapper().writeValueAsString(message));
                    out.flush();
                    out.close();
                })
               .and()
                //抛弃默认未认证行为,默认的未认证是默认定向到登录页面
                /*
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        String redirectUrl;
                        if (!this.useForward) {
                            redirectUrl = this.buildRedirectUrlToLoginPage(request, response, authException);
                            this.redirectStrategy.sendRedirect(request, response, redirectUrl);
                        } else {
                            redirectUrl = null;
                            if (this.forceHttps && "http".equals(request.getScheme())) {
                                redirectUrl = this.buildHttpsRedirectUrlForRequest(request);
                            }

                            if (redirectUrl != null) {
                                this.redirectStrategy.sendRedirect(request, response, redirectUrl);
                            } else {
                                String loginForm = this.determineUrlToUseForThisRequest(request, response, authException);
                                logger.debug(LogMessage.format("Server side forward to: %s", loginForm));
                                RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);
                                dispatcher.forward(request, response);
                            }
                        }
                    }
                 */
                .exceptionHandling()
                //authenticationEntryPoint() 参数是 AuthenticationEntryPoint  需要实现的方法是:void commence(HttpServletRequest var1, HttpServletResponse var2, AuthenticationException var3) throws IOException, ServletException;
                //响应信息从重定向到登录页面---->尚未登录,请先登录
                .authenticationEntryPoint((request,response,e)->{
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write("尚未登录,请先登录");
                    out.flush();
                    out.close();
                })


                // 前后端不分离的配置
//            .defaultSuccessUrl("/pmsBrand/listAll")

              //前后端分离,使用JSON 返回给前端数据,不管是成功还是失败,成功就用successHandler

            .and()
            .csrf().disable();//表示防止跨域功能禁用



        //b.jz-mall
//        http.csrf().disable()//使用JWT,不需要防范crsf(跨域)攻击,为何?
//                .sessionManagement()//基于token,是一种无状态登录,不需要session,使用session是有状态登录


    }

    //江南一点雨:
    // 这个配置类的作用?
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //当在配置文件yml里添加了SpringSecurity.user =XX 信息后,会自动覆盖掉,SpringSecurity自带生成的密码
        //不连接数据库的情况下 --> auth.inMemoryAuthentication()开启内存中定义用户,这里存在一个疑问,到底是配置文件里的内容生效还是这里指定的生效,还是两者都生效
        //答案是:当在配置类中指定了用户名和密码,那么yml文件中的配置会被覆盖掉
            auth.inMemoryAuthentication() //开启内存
                    .withUser("chenliang")//指定用户名
                    .password("456")//指定密码
                    .roles("admin");//指定角色名

    }
    /**
     * 4.自定义数据库连接 和UserDetails 有关系
     */


    /**
     * 5.对密码进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //a.江南一点雨
        return NoOpPasswordEncoder.getInstance();//这是对密码不进行加密的情况,
//        开启内存中定义用户,不能加上加密,如果密码解密器不是NoOpPasswordEncoder,会报错Encoded password does not look like BCrypt

        //b.jz-mall
//        return new BCryptPasswordEncoder();//通过这种加密方式,可以指定版本
    }


    /**
     * 6.对异常进行处理
     */
}
