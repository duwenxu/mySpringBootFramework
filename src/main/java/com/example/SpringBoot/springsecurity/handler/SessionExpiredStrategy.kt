package com.example.springboot.springsecurity.handler

import com.example.springboot.common.commonutils.ext.resp
import com.example.springboot.common.result.ResultEnum
import com.example.springboot.common.result.ResultTo
import org.springframework.security.web.session.SessionInformationExpiredEvent
import org.springframework.security.web.session.SessionInformationExpiredStrategy
import org.springframework.stereotype.Component


/**
 * session 过期的处理策略
 */

@Component
class SessionExpiredStrategy:SessionInformationExpiredStrategy{

    /**
     * 检测到过期的session时
     * @param event SessionInformationExpiredEvent?
     */
    override fun onExpiredSessionDetected(event: SessionInformationExpiredEvent?) {
        event?.response
                ?.resp(ResultTo(ResultEnum.UNLOGIN,"登陆失效").toJsonStr())
    }
}