//package com.example.springboot.kafka.producer
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.kafka.core.KafkaTemplate
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
//    lateinit var myTemplate:KafkaTemplate<Int,String>       //直接使用在 kafkaConfig 中注入的Bean对象即可
//
//    fun sendMessage(message: String) = myTemplate.send("test0", message)
//}