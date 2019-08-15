package com.example.springboot.springsecurity

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


/**
 * springSecurity的配置文件
 *
 * springSecurity的原理：
 *      使用很多拦截器对url进行拦截，以此来管理登陆验证和用户权限验证
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity?) {
        val tHttp=http ?:return
        tHttp
                .formLogin().loginPage("/login")   //定义登陆时的跳转页面
                .loginProcessingUrl("/api/user/simpleLogin")   //登陆的url，不需要自己实现
                .failureUrl("/login-error")    //定义登陆出错时的页面
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
    }

    @Bean
    open fun passwordEncoder()=DefinePasswordEncoder

    /**
     * //将用户名和密码在代码中写死   实际中肯定不这么搞
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    @Throws(Exception::class)
    fun configAdmin(auth:AuthenticationManagerBuilder){
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("admin,superUser")
                .and()
                .withUser("user1").password("123456").roles("systemUser")
    }
}

