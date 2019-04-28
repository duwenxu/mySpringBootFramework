package com.example.SpringBoot.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

//@Configuration
class RedisConfig{

    @Value("\${spring.redis.database}")
    var datatbase:Int=0

    @Value("\${spring.redis.host}")
    var host:Int=0
//
//    @Bean   //@Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。添加的bean的id为方法名
//    fun stringRedis(connFactory: RedisConnectionFactory) = StringRedisTemplate(connFactory)




}