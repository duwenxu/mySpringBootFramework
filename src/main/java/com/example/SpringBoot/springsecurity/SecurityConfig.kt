package com.example.springboot.springsecurity

import com.example.springboot.springsecurity.authentication.MyAuthenticationProvider
import com.example.springboot.springsecurity.handler.AccessDeniedHandler
import com.example.springboot.springsecurity.handler.LoginHandler
import com.example.springboot.springsecurity.handler.LogoutHandler
import com.example.springboot.springsecurity.handler.SessionExpiredStrategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * springSecurity的配置文件
 *
 * springSecurity的原理：
 *      使用很多拦截器对url进行拦截，以此来管理登陆验证和用户权限验证
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Autowired
    lateinit var provider:MyAuthenticationProvider

    @Autowired
    lateinit var loginHandler:LoginHandler

    @Autowired
    lateinit var logoutHandler:LogoutHandler

    @Autowired
    lateinit var accessDeniedHandle:AccessDeniedHandler

    @Autowired
    lateinit var sessionExpiredStrategy: SessionExpiredStrategy

//    @Autowired
//    lateinit var sessionRegistry:SessionRegistry   //todo   SessionRegistry无法注入

    /**
     * //将用户名和密码在代码中写死   实际中肯定不这么搞
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    @Throws(Exception::class)
    fun configAdmin(auth:AuthenticationManagerBuilder){
        auth.authenticationProvider(provider)    //通过认证服务从数据库中获取密码进行验证
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("admin,superUser")
//                .and()
//                .withUser("user1").password("123456").roles("systemUser")
    }

    override fun configure(http: HttpSecurity?) {
        val tHttp=http ?:return
        tHttp
                .formLogin().loginPage("/login")   //定义登陆时的跳转页面
//                .loginProcessingUrl("/api/user/simpleLogin")
                .failureUrl("/login-error")    //定义登陆出错时的页面
                .permitAll()
                .and()

                .authorizeRequests().antMatchers("/index").permitAll()   //表示 /index这个页面不需要权限认证，所有人都可以访问
                .antMatchers("/personalInfo").hasRole("ADMIN")      //表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                //表示Delete请求须同时满足hasRole和hasIpAddress两种权限   ADMIN会自动使用 ROLE_前缀，即此时的用户角色为 ROLE_ADMIN
                .antMatchers(HttpMethod.GET,"/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/user/*").hasIpAddress("localhost")
                .antMatchers(HttpMethod.DELETE,"/user/*").access("hasRole('ADMIN') and hasIpAddress('127.0.0.1')")
                .anyRequest().authenticated()       //authenticated() 允许认证的用户进行访问
                .and()

                .addFilterBefore(userAuthenticationFilter(),UsernamePasswordAuthenticationFilter::class.java)   //添加前一个filter 相当于是构造一个filterChain:过滤链

                .logout()
                .logoutUrl("/api/user/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutHandler)
                .invalidateHttpSession(true)   //配置SecurityContextLogoutHandler使登出后session失效
                .and()

                .exceptionHandling()  //todo
                .authenticationEntryPoint(accessDeniedHandle)
                .accessDeniedHandler(accessDeniedHandle)
                .and()

                //session配置
                .sessionManagement()   //todo
                .maximumSessions(1)   //todo
                .expiredSessionStrategy(sessionExpiredStrategy)  //session过期策略
//                .sessionRegistry(sessionRegister)
                .maxSessionsPreventsLogin(false)   //todo
                .and()
                .and()  //todo

                .csrf().disable()  //todo

    }

    /**
     * 用户验证filter
     * @return UsernamePasswordAuthenticationFilter
     */
    private fun userAuthenticationFilter():UsernamePasswordAuthenticationFilter{
        val filter= UsernamePasswordAuthenticationFilter()
        filter.setAuthenticationSuccessHandler(loginHandler)
        filter.setAuthenticationFailureHandler(loginHandler)
        filter.setAuthenticationManager(authenticationManager())    //authenticationManager:
        filter.setFilterProcessesUrl("/api/user/simpleLogin")       //登陆的url，不需要自己实现   可以直接放在configure里，也可以加在这个filter里
        return filter
    }

    @Bean
    open fun passwordEncoder()=DefinePasswordEncoder


}

