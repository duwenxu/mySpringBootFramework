package com.example.SpringBoot.webSocket

import com.alibaba.fastjson.JSONObject
import com.example.SpringBoot.webSocket.sessionConfigurator.GetHttpSessionConfigurator
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint


@Component
@ServerEndpoint("/myendpoint/{userId}", configurator = GetHttpSessionConfigurator::class)    //这里使用的是KClass
/**
 * @Description: 监听指定webSocket的端点，并对相应事件做出处理和打印日志   不影响实际的数据推送功能
 * @Author: duwenxu
 * @Date: 2019/3/12
 */
class WebSocketListener {
    companion object {              //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法
        private val log = LoggerFactory.getLogger(WebSocketListener::class.java)
        //在线数
        private var onlineCount = 0

        //map用来存放每个客户端对应的webSocket对象
        //@todo  后续可以用缓存实现
        private val webSocketClients = ConcurrentHashMap<String, WebSocketListener>()

        @Synchronized
        private fun addOnlineCount() {     //kotlin中的同步代码块/方法   用@Synchronized
            onlineCount++
        }

        @Synchronized
        private fun subOnlineCount() {
            onlineCount--
        }

        @Synchronized
        private fun getOnlineCount(): Int {
            return onlineCount
        }

        @Synchronized
        private fun getWebSocketClients(): ConcurrentHashMap<String, WebSocketListener> {
            return webSocketClients
        }
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private var session: Session? = null

    //标识不同的客户端
    private var userId: String = ""

    @OnOpen
    fun open(@PathParam("userId") userId: String, session: Session) {
        this.userId = userId
        this.session = session
        addOnlineCount()
        webSocketClients[userId] = this
        println("$userId 号用户已连接")
    }

    @OnClose
    @Throws(IOException::class)
    fun close(@PathParam("userId") userId: String, session: Session) {
        webSocketClients.remove(userId)
        subOnlineCount()
        println("$userId 号用户已断开")
    }

    //接收消息后处理
    @OnMessage
    @Throws(IOException::class)
    fun onMessage(message: String) {
        val jsonObject = JSONObject.parseObject(message)
        val ms = jsonObject.getString("message")
        if (jsonObject.getString("targetId") != "All") {
            sendMessageTo(ms, jsonObject["targetId"].toString())   //按照客户端传递过来的Id进行 点播
        } else {
            sendMessageAll(ms)     //发送给所有人
        }
    }

    //点播  发给某一个用户
    @Throws(IOException::class)
    fun sendMessageTo(message: String, targetId: String) {
        webSocketClients.values.forEach {
            /**
             * websocket session发送文本消息有两个方法 ： getAsyncRemote () 和getBasicRemote ()
             *  getAsyncRemote是非阻塞式的，getBasicRemote是阻塞式的
             */

            if (it.userId == targetId) {
                it.session!!.asyncRemote.sendText(message)
            }
        }
    }


    //广播  发给所有用户
    @Throws(IOException::class)
    fun sendMessageAll(message: String) {
        webSocketClients.values.forEach {
            it.session!!.asyncRemote.sendText(message)
        }
    }
}