package com.example.SpringBoot.Kafka.consumer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
/** 
* @Description: 消费者 监听器 类
* @Author: duwenxu 
* @Date: 2019/3/19 
*/ 
//topic：myTopicTest
@Component
class KafkaListenerTest:Thread() {
    val log = LoggerFactory.getLogger(KafkaListenerTest::class.java)
    /**
     * @Description: 配置消费topic及group
     * @Author: duwenxu
     * @Date: 2019/3/18
     */
    @KafkaListener(topics = ["test0"],containerFactory = "signalContainerFactory")
    fun consumer(message: String) = log.info("group=test,message=$message")
}