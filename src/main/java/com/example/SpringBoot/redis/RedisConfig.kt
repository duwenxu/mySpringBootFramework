package com.example.springboot.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class RedisConfig{

    @Value("\${spring.redis.timeout}")
    var timeOut:Long=0

    @Value("\${spring.redis.database}")
    var datatbase:Int=0

    @Value("\${spring.redis.host}")
    lateinit var rhost:String

    @Value("\${spring.redis.port}")
    var rport:Int=0

    @Value("\${spring.redis.password}")
    var rpossword:String=""

    @Value("\${spring.redis.jedis.pool.max-active}")
    var maxActive:Int=10

    @Value("\${spring.redis.jedis.pool.max-wait}")
    var maxWait:Int=0

    @Value("\${spring.redis.jedis.pool.max-idle}")
    var maxIdle:Int=0

    @Value("\${spring.redis.jedis.pool.min-idle}")
    var minIdle:Int=0

    @Bean
    open fun jedisConnectionFactory(){

    }




}