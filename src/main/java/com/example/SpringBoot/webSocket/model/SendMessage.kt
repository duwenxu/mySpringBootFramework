package com.example.SpringBoot.webSocket.model

//用来接受客户端发送消息的内容
class SendMessage {
    lateinit var targetId:String
    lateinit var message: String
    lateinit var date: String
}