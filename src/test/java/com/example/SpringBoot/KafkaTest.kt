//package com.example.SpringBoot
//
//import com.example.SpringBoot.Kafka.Config.KafkaConfig
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.context.junit4.SpringRunner
//
//@RunWith(SpringRunner::class)
//@SpringBootTest
//class KafkaTest {
//    @Autowired
//    lateinit var config: KafkaConfig
//
//    @Test
//    fun testDefaultKafkaTemplate() {
//        config.defaultKafkaTemplate().sendDefault("testDefaultKafkaTemplate_Message ")
//    }
//}