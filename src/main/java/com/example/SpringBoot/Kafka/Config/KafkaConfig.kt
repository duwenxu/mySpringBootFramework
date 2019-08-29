//package com.example.springboot.kafka.config
//
//import org.apache.kafka.clients.consumer.ConsumerConfig
//import org.apache.kafka.clients.producer.ProducerConfig
//import org.apache.kafka.common.serialization.*
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//import org.springframework.kafka.annotation.EnableKafka
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
//import org.springframework.kafka.config.KafkaListenerContainerFactory
//import org.springframework.kafka.core.*
//import org.springframework.kafka.listener.ContainerProperties.AckMode
//
//@Configuration
//@EnableKafka    //开启Kafka
//open class KafkaConfig {
//    @Value("\${spring.kafka.bootstrap-servers}")
//    lateinit var bootstrap_servers: String
//
//    @Value("\${spring.kafka.consumer.group-id}")
//    lateinit var consumer_groupId: String
//
//    @Value("\${spring.kafka.consumer.max-poll-records}")
//    var maxPollRecords: Int = 50
//
//    @Value("\${spring.kafka.listener.concurrency}")
//    var conCurrency: Int = 3
//
//    @Value("\${spring.kafka.consumer.auto-commit-interval}")
//    lateinit var autoCommitInterval: String
//
//    /**
//     * 配置消费者factory
//     * @return ConsumerFactory<Any,Any> 消费者factory Bean
//     * @Author: duwenxu
//     * @Date: 2019/3/18
//     */
//    @Bean
//    open fun consumer(): ConsumerFactory<Any, Any> {
//        val config = HashMap<String, Any>()    //HashMap()类型的配置参数
//        //连接地址
//        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap_servers
//        //GroupID
//        config[ConsumerConfig.GROUP_ID_CONFIG] = consumer_groupId
//        //是否自动提交
//        config[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
//        //自动提交的频率
//        config[ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG] = autoCommitInterval
//        //批量消费数
//        config[ConsumerConfig.MAX_POLL_RECORDS_CONFIG] = maxPollRecords
//        //Session超时设置
//        config[ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG] = 30000
//        //键的反序列化方式
//        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java    //这里要用java的反射类
//        //值的反序列化方式
//        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
//        //返回消费者工厂的实现类
//        return DefaultKafkaConsumerFactory<Any, Any>(config)
//    }
//
//    /**
//     * 配置生产者factory
//     * @return ProducerFactory<Any,Any> 生产者factory Bean
//     * @Author: duwenxu
//     * @Date: 2019/3/18
//     */
//    @Bean
//    open fun producer(): ProducerFactory<Int, String> {
//        val config = HashMap<String, Any>()
//        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrap_servers
//        //1表示启用重试，0为不启用重试机制
//        config[ProducerConfig.RETRIES_CONFIG] = 1
//        config[ProducerConfig.BATCH_SIZE_CONFIG] = 16384
//        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
//        config[ProducerConfig.LINGER_MS_CONFIG] = 1
//        config[ProducerConfig.BUFFER_MEMORY_CONFIG] = 1024000
//        //键的序列化方式
//        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
//        //值的序列化方式
//        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
//        return DefaultKafkaProducerFactory<Int, String>(config)
//    }
//
//    /**
//     * @Description: 批量消费的factory  线程数为配置的concurrency
//     * @Author: duwenxu
//     * @Date: 2019/3/18
//     */
//    @Bean
//    open fun batchContainerFactory(consumerFactory: ConsumerFactory<Any, Any>): KafkaListenerContainerFactory<*> {
//        val containerFactory = ConcurrentKafkaListenerContainerFactory<Any, Any>()
//        containerFactory.consumerFactory = consumerFactory    //设置消费者工厂为配置好的consumerFactory
//        containerFactory.setConcurrency(conCurrency)   //设置同时消费的线程数
//        containerFactory.isBatchListener = true   //批量消费
//        containerFactory.containerProperties.ackMode = AckMode.MANUAL_IMMEDIATE
//        return containerFactory
//    }
//
//    /**
//     * @Description: 批量消费的factory  线程数为1
//     * @Author: duwenxu
//     * @Date: 2019/3/18
//     */
//    @Bean
//    open fun signalContainerFactory(consumerFactory: ConsumerFactory<Any, Any>): KafkaListenerContainerFactory<*> {
//        val containerFactory = ConcurrentKafkaListenerContainerFactory<Any, Any>()
//        containerFactory.consumerFactory = consumerFactory    //设置消费者工厂为配置好的consumerFactory
//        containerFactory.setConcurrency(conCurrency)   //设置同时消费的线程数
//        containerFactory.isBatchListener = true   //批量消费
//        containerFactory.containerProperties.ackMode = AckMode.MANUAL_IMMEDIATE
//        return containerFactory
//    }
//
//    /**
//     * kafkaTemplate实现了Kafka发送接收等功能
//     */
//    @Bean("myKafkaTemplate")
//    @Primary   //@Primary注解：在拥有多个同类型的Bean时，优先使用该Bean
//    open fun myKafkaTemplate() = KafkaTemplate<Int, String>(producer())
//
//    /**
//     * kafka中发送消息方法
//     *                 sendDefault()及其重载方法
//     *                 send()及其重载方法
//     *
//     *指定默认发送topic的KafkaTemplate
//     */
//    @Bean("defaultKafkaTemplate")
//    open fun defaultKafkaTemplate(): KafkaTemplate<Int, String> {
//        val template=KafkaTemplate<Int,String>(producer())
//        template.defaultTopic="test0"
//        return template
//    }
//}
