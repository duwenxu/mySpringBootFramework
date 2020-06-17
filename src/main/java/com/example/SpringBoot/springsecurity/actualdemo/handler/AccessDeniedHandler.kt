package com.example.springboot.springsecurity.actualdemo.handler

import com.example.springboot.common.commonutils.ext.resp
import com.example.springboot.common.result.ResultEnum
import com.example.springboot.common.result.ResultTo
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 访问鉴权
 */

@Component
class AccessDeniedHandler: AuthenticationEntryPoint, AccessDeniedHandler{

    /**
     * 开启验证方案方法
     * @param request HttpServletRequest?
     * @param response HttpServletResponse?
     * @param authException AuthenticationException?
     */
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        response
                ?.resp(ResultTo(ResultEnum.UNLOGIN,"${authException?.message}").toJsonStr())
    }

    /**
     * 已登录 拒绝访问处理
     * @param request HttpServletRequest?
     * @param response HttpServletResponse?
     * @param accessDeniedException AccessDeniedException?
     */
    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        response
                ?.resp(ResultTo(ResultEnum.OPERATION_FAILED,"${accessDeniedException?.message}").toJsonStr())
    }
}