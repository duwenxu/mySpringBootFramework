//package com.example.SpringBoot.Kafka.producer
//
//import com.example.springboot.Kafka.Config.KafkaConfig
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Component
//
///**
// * @Description: 生产者发送消息
// * @Author: duwenxu
// * @Date: 2019/3/19
// */
//@Component
//class KafkaProducerTest:Thread() {
//    @Autowired
//    lateinit var config: KafkaConfig//注入的bean对象需要 初始化或延迟初始化
//
//    fun sendMessage(message: String) = config.myKafkaTemplate().send("test0", message)
//}