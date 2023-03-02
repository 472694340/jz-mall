package com.jz.mall.config;

/**
 *  SpringSecurity配置类
 *    1.对静态资源放行
 *    2.对登录接口放行,对其他接口访问进行验证 .需要添加验证过滤器
 *    3.自定义数据库连接,SpringSecurity 配置数据源,被封装在UserDetailService 接口中
 *    4.对密码进行加密
 *    5.对访问异常进行处理
 */
//public class SpringSecurityConfigJN extends WebSecurityConfigurerAdapter
public class SpringSecurityConfigJN {

//    @Autowired
//    AdminUserDetailsServiceImpl userDetailsService;
//
//
//    /**
//     * 1.对静态资源进行放行(静态资源是和web浏览器有关系)
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");//ignoring()用来配置忽略掉的URL
//    }
//
//
//    /**
//     *  2.对接口请求进行拦截配置
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //a.江南一点雨
//        http.authorizeRequests().anyRequest().authenticated()//authorizeRequests ---> (intercept-url)表示拦截的URL  //表示所有的URL都会被拦截
//            .and()//and()表示结束这个标签,开始下一轮配置
//            .formLogin()//指定登录成功后的跳转页面,不指定的话就是默认的index,没有index页面就会报404,这是前后端不分离的适配的
//            .loginPage("/login.html").permitAll()  //表示让SpringSecurity去找一个login.html页面作为登录页面,同时因为放行了静态资源html,所以可以正常访问//这里的loginPage默认设置都在FormLoginConfigurer类中
//                //前后端分离的配置 采用successHandler 和 failureHandler
//                //successHandler 方法的参数是一个 AuthenticationSuccessHandler 对象，这个对象中我们要实现的方法是 onAuthenticationSuccess。  void onAuthenticationSuccess(HttpServletRequest var1, HttpServletResponse var2, Authentication var3) throws IOException, ServletException;
//                //登录成功后,返回的是: {"password":null,"username":"chenliang","authorities":[{"authority":"ROLE_admin"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
//                .successHandler((request,response,authentication)->{
//                    Object principal = authentication.getPrincipal();
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out  = response.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(principal));
//                    out.flush();
//                    out.close();
//                })
//                //failureHandler()的参数是:AuthenticationFailureHandler 对象  要实现的方法是:void onAuthenticationFailure(HttpServletRequest var1, HttpServletResponse var2, AuthenticationException var3) throws IOException, ServletException;
//                //登录失败返回的信息是; "Bad credentials"
//                .failureHandler((request,response,e)->{
//                    response.setContentType("application/json;charset=utf-8");
//                    String message = e.getMessage();
//                    PrintWriter out  = response.getWriter();
//                    //这里失败的异常可以进一步细化处理
////                    if (e instanceof RuntimeException){
////                        message = "运行时异常";
////                    }
//                    out.write(new ObjectMapper().writeValueAsString(message));
//                    out.flush();
//                    out.close();
//                })
//               .and()
//                //抛弃默认未认证行为,默认的未认证是默认定向到登录页面
////                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
////                        String redirectUrl;
////                        if (!this.useForward) {
////                            redirectUrl = this.buildRedirectUrlToLoginPage(request, response, authException);
////                            this.redirectStrategy.sendRedirect(request, response, redirectUrl);
////                        } else {
////                            redirectUrl = null;
////                            if (this.forceHttps && "http".equals(request.getScheme())) {
////                                redirectUrl = this.buildHttpsRedirectUrlForRequest(request);
////                            }
////                            if (redirectUrl != null) {
////                                this.redirectStrategy.sendRedirect(request, response, redirectUrl);
////                            } else {
////                                String loginForm = this.determineUrlToUseForThisRequest(request, response, authException);
////                                logger.debug(LogMessage.format("Server side forward to: %s", loginForm));
////                                RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);
////                                dispatcher.forward(request, response);
////                            }
////                        }
////                    }
//
//                //authenticationEntryPoint() 参数是 AuthenticationEntryPoint  需要实现的方法是:void commence(HttpServletRequest var1, HttpServletResponse var2, AuthenticationException var3) throws IOException, ServletException;
//                //响应信息从重定向到登录页面---->尚未登录,请先登录
//                .exceptionHandling()
//                .authenticationEntryPoint((request,response,e)->{
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    out.write("尚未登录,请先登录");
//                    out.flush();
//                    out.close();
//                })
//
//                // 前后端不分离的配置
////            .defaultSuccessUrl("/pmsBrand/listAll")
//            .and()
//            .csrf().disable();//表示防止跨域功能禁用
//    }
//
//
//    /**
//     *   UserDetailsService 接口,有四个实现类分别支持不同的数据源
//     *      1. SpringSecurity 内置数据库模型  public class JdbcUserDetailsManager extends JdbcDaoImpl implements UserDetailsManager, GroupManager {}
//     *      2. 自定义数据库?                  public class JdbcDaoImpl extends JdbcDaoSupport implements UserDetailsService, MessageSourceAware{}  配置数据源
//     *      3. 缓存数据库?                    public class CachingUserDetailsService implements UserDetailsService {} 缓存
//     *      4. 在java代码中指定?              public class InMemoryUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {}  java代码里指定
//
//     * 3.连接数据库 (目的是为了和服务器数据库关联,将数据库中的用户信息注入到SpringSecurity的封装类)
//     *   思考:需要返回的是UserDetailService接口的实现类实例
//     *        1.(江南一点雨):自定义一个类,去实现这个UserDetailsService接口,需要从数据库中取出用户信息(用户信息和权限信息)
//     *        2.(mall) 通过 用户类  继承UserDetails接口, 用户类是和数据库相关联的
//     *   那么这里到容器里的,应该是将用户名和权限都查出来返回给UserDetailsService的实例?===> 对的
//     */
//    //江南一点雨:
//    // AuthenticationManagerBuilder auth这个配置类的作用?  =======>配置用户信息
//    //
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //当在配置文件yml里添加了SpringSecurity.user =XX 信息后,会自动覆盖掉,SpringSecurity自带生成的密码
//        //不连接数据库的情况下 --> auth.inMemoryAuthentication()开启内存中定义用户,这里存在一个疑问,到底是配置文件里的内容生效还是这里指定的生效,还是两者都生效
//        //答案是:当在配置类中指定了用户名和密码,那么yml文件中的配置会被覆盖掉
////            auth.inMemoryAuthentication() //开启内存
////                    .withUser("chenliang")//指定用户名
////                    .password("456")//指定密码
////                    .roles("admin");//指定角色名
//        auth.userDetailsService(userDetailsService);
//
//    }
//
//    /**
//     * 4.对密码进行加密
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //a.江南一点雨
////        return NoOpPasswordEncoder.getInstance();//这是对密码不进行加密的情况,
////        开启内存中定义用户,不能加上加密,如果密码解密器不是NoOpPasswordEncoder,会报错Encoded password does not look like BCrypt
//
//        //b.jz-mall
//        return new BCryptPasswordEncoder();//通过这种加密方式,可以指定版本
//    }

}
