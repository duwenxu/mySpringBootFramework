package com.example.springboot.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

/**
 * @Description:  开启WebSocket支持
 * @Param:
 * @return:
 * @Author: duwenxu
 * @Date: 2019/3/7
 */
@Configuration
@EnableWebSocketMessageBroker
//启用websocket消息处理，以及收发消息的域
open class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    //配置消息代理前缀
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic","/socket")  //在topic这个域上可以向客户端发送消息,可以设置多个              服务端->客户端
        registry.setApplicationDestinationPrefixes("/app","/socket")        //这句表示客户端向服务端发送时的主题上面需要加"/app"作为前缀；   客户端->服务端

        //example:        config.setUserDestinationPrefix("/user/");这句表示给指定用户发送（一对一）的主题前缀是“/user/”;
    }

    /**
     *  portfolio-stomp就是websocket的端点，客户端需要注册这个端点进行链接，withSockJS允许客户端利用sockjs进行浏览器兼容性处理
     *  registry.addEndpoint("/portfolio-stomp")/*.setAllowedOrigins("http://localhost:8080")*/.withSockJS();
     */
    //这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
                .addEndpoint("/ws")    //添加webSocket端点
                .withSockJS()
    }
}