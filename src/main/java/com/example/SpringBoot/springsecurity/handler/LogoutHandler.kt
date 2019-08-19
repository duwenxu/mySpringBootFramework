package com.example.springboot.springsecurity.handler

import com.example.springboot.common.commonutils.ext.resp
import com.example.springboot.common.result.ResultTo
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 登出时的处理
 */
@Component
class LogoutHandler:LogoutHandler,LogoutSuccessHandler{

    /**
     * 登出
     * @param request HttpServletRequest?
     * @param response HttpServletResponse?
     * @param authentication Authentication?
     */
    override fun logout(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        val username = request?.getAttribute("username").toString()
        //todo 清理用户缓存
    }

    /**
     * 注销成功
     * @param request HttpServletRequest?
     * @param response HttpServletResponse?
     * @param authentication Authentication?
     */
    override fun onLogoutSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        response?.resp(ResultTo("注销成功").toJsonStr())
    }
}