package com.example.springboot.springsecurity

import com.example.springboot.springsecurity.authentication.MyAuthenticationProvider
import com.example.springboot.springsecurity.loginhandler.AuthenticationResponeseHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
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
    lateinit var authenHandler:AuthenticationResponeseHandler

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
                .antMatchers("/personalInfo").hasRole("admin")      //表示/whoim的这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                .anyRequest().authenticated()       //必须经过认证以后才能访问
                .and()
                .csrf().disable()
                .addFilterBefore(userAuthenticationFilter(),UsernamePasswordAuthenticationFilter::class.java)   //添加前一个filter 相当于是构造一个filterChain:过滤链
    }

    private fun userAuthenticationFilter():UsernamePasswordAuthenticationFilter{
        val filter= UsernamePasswordAuthenticationFilter()
        filter.setAuthenticationSuccessHandler(authenHandler)
        filter.setAuthenticationFailureHandler(authenHandler)
        filter.setAuthenticationManager(authenticationManager())    //authenticationManager:
        filter.setFilterProcessesUrl("/api/user/simpleLogin")       //登陆的url，不需要自己实现   可以直接放在configure里，也可以加在这个filter里
        return filter
    }

    @Bean
    open fun passwordEncoder()=DefinePasswordEncoder


}

