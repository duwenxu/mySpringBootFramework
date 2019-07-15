package com.example.springboot.redis

import org.springframework.beans.factory.annotation.Value

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