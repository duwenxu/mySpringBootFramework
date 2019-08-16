package com.example.springboot.websocket

import com.example.springboot.websocket.model.SendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*

@Controller
class WebScoketController {

    @Autowired
    lateinit var messagingTemplate: SimpMessagingTemplate

    /**
     * @Description: 接受客户端发送过来的消息
     * @Author: duwenxu
     * @Date: 2019/3/8
     */
    @MessageMapping("/send")
    @SendTo("/topic/send")
    @Throws(Exception::class)  //kotlin中抛出异常方式
    fun send(message: SendMessage): SendMessage {
        var df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        message.date = df.format(Date())
        return message//接收客户端发送的请求
    }

    @Scheduled(fixedRate = 500)   //定时 指定被注释方法的执行间隔   @EnableScheduling注解为：启用spring boot的定时任务，这与“callback”方法相呼应，用于每隔1秒推送服务器端的时间。
    @SendTo("/topic/callback")
    @Throws(java.lang.Exception::class)
    fun callback(): Any? {
        var df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        messagingTemplate.convertAndSend("/topic/callback", df.format(Date()))   //发消息
        return "callback"
    }

    //在程序中的其他地方要发送动态消息时
    //WebScoketController.messagingTemplate.convertAndSend("发送路径",发送内容)
}