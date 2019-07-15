package com.example.springboot.websocket.sessionConfigurator

import javax.websocket.HandshakeResponse
import javax.websocket.server.HandshakeRequest
import javax.websocket.server.ServerEndpointConfig
import javax.websocket.server.ServerEndpointConfig.Configurator

/** 
* @Description: 获取HttpSession的配置类   在 ServerEndpoint中添加Session
* @Param:  
* @return:  
* @Author: duwenxu 
* @Date: 2019/3/13 
*/ 
class GetHttpSessionConfigurator: Configurator() {
    
    override fun modifyHandshake(sec: ServerEndpointConfig?, request: HandshakeRequest?, response: HandshakeResponse?) {
        val httpSession = request?.httpSession
        sec!!.userProperties[httpSession!!::class.java.name] = httpSession   //保证httpSession不为空，为空就报错
    }
}