package com.example.springboot.redis.config

import org.codehaus.jackson.map.ser.std.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import redis.clients.jedis.JedisPoolConfig

/**
 *  redis 配置类
 * @property timeOut Long
 * @property database Int
 * @property host String
 * @property port Int
 * @property password String
 * @property maxTotal Int
 * @property maxWait Int
 * @property maxIdle Int
 * @property minIdle Int
 */

@Configuration
open class RedisConfig {

    @Value("\${spring.redis.timeout}")
    var timeOut: Long = 0

    @Value("\${spring.redis.database}")
    var database: Int = 0

    @Value("\${spring.redis.host}")
    lateinit var host: String

    @Value("\${spring.redis.port}")
    var port: Int = 0

    @Value("\${spring.redis.password}")
    lateinit var password: String

    @Value("\${spring.redis.jedis.pool.max-active}")
    var maxTotal: Int = 10

    @Value("\${spring.redis.jedis.pool.max-wait}")
    var maxWait: Long = 0

    @Value("\${spring.redis.jedis.pool.max-idle}")
    var maxIdle: Int = 0

    @Value("\${spring.redis.jedis.pool.min-idle}")
    var minIdle: Int = 0


    @Bean
    open fun connectionFactory()=differFactoryByDB(database)

    /**
     * 注入 StringRedisTemplate Bean
     * @return StringRedisTemplate
     * StringRedisTemplate类： 基于String的redisTemplate的最小化通用配置
     */
    @Bean
    open fun redisTemplate()=StringRedisTemplate()

    /**
     * 自定义序列化方式的  redisTemplate<Int,String>类型的bean
     * @return IntRedisTemplate
     */
    @Bean
    open fun intRedisTemplate():IntRedisTemplate{
        val iTemplate=IntRedisTemplate()
        iTemplate.connectionFactory=differFactoryByDB(database)
        val strSerializer= StringRedisSerializer()
        iTemplate.keySerializer=strSerializer
        iTemplate.hashKeySerializer=strSerializer   //hash序列化：todo
        val mySerializer=MyRedisSerializer()
        iTemplate.valueSerializer=mySerializer
        iTemplate.hashValueSerializer=mySerializer
        return iTemplate
    }




    /**
     * 获取不同数据库的连接
     * @param dbIndex Int
     * @return JedisConnectionFactory
     */
   private fun differFactoryByDB(dbIndex:Int)=JedisConnFactory.getConnFactory(host,port,dbIndex,password,timeOut,maxTotal,maxIdle,minIdle,maxWait)

}