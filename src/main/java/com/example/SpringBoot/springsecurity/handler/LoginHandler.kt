package com.example.springboot.springsecurity.handler

import com.example.springboot.common.commonutils.ext.resp
import com.example.springboot.common.result.ResultEnum
import com.example.springboot.common.result.ResultTo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 自定义的登陆验证结果返回
 */
@Component
class LoginHandler : AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Autowired
    lateinit var sessionRegisity: SessionRegistry

    /**
     * 登陆成功后的响应
     * @param request HttpServletRequest?
     * @param response HttpServletResponse?
     * @param authentication Authentication?
     */
    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        authentication?.let {
            val usetinfo = it.principal
            sessionRegisity.registerNewSession(request!!.session.id, usetinfo)
            response?.resp(ResultTo(usetinfo).toJsonStr())
        }
    }

    /**
     * 登陆失败
     * @param request HttpServletRequest?
     * @param response HttpServletResponse?
     * @param exception AuthenticationException?
     */
    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?, exception: AuthenticationException?) {
        response?.resp(
                ResultTo(ResultEnum.OPERATION_FAILED, "${exception?.message}").toJsonStr()
        )
    }
}