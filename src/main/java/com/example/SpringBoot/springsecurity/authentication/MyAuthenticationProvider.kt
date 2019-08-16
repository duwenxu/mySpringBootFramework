package com.example.springboot.springsecurity.authentication

import com.example.springboot.springsecurity.model.UserInfo
import com.example.springboot.springsecurity.service.UserinfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
/**
 * 认证服务提供者
 * @property userinfoService UserinfoService
 */
@Component
class MyAuthenticationProvider : AuthenticationProvider {

    @Autowired
    lateinit var userinfoService: UserinfoService

    /**
     * 用户信息认证
     * @param authentication Authentication?
     * @return Authentication
     */
    override fun authenticate(authentication: Authentication?): Authentication {
        val username = authentication?.name
        val password = authentication?.credentials as String

        val userinfo = userinfoService.loadUserByUsername(username) as UserInfo? ?: throw BadCredentialsException("用户名不存在")
        if (userinfo.authorCode!=password) throw BadCredentialsException("密码不正确")
        val authorities=userinfo.authorities

        return UsernamePasswordAuthenticationToken(userinfo,password,authorities)
    }

    /**
     * true 表示支持这个 验证操作
     * @param authentication Class<*>?
     * @return Boolean
     */
    override fun supports(authentication: Class<*>?)=true
}