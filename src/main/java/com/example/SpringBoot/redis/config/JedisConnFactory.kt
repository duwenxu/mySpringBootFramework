package com.example.springboot.redis.config

import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.stereotype.Component
import java.time.Duration

@Component
object JedisConnFactory {


    /**
     * 配置连接factory
     * @param host String
     * @param port Int
     * @param dataBase Int
     * @param password String?
     * @param outTime Long
     * @param maxTotal Int
     * @param maxIdle Int
     * @param minIdle Int
     * @return JedisConnectionFactory
     *
     * JedisConnectionFactory  可以看源码里的注释(-----------------看源码的重要性)
     */
    fun getConnFactory(host: String, port: Int, dataBase: Int, password: String?, outTime: Long, maxTotal: Int, maxIdle: Int,minIdle:Int,maxWrite:Long): JedisConnectionFactory {
        val standaloneConfig = redisStandaloneConfiguration(host, port, dataBase, password)
        val clientConfig = jedisClientConfiguration(outTime)
        val poolConfig = clientConfig.poolConfig.orElse(null)
        poolConfig.maxIdle=maxIdle
        poolConfig.maxTotal= maxTotal
        poolConfig.minIdle=minIdle
        poolConfig.maxWaitMillis=maxWrite
        val connectionFactory = JedisConnectionFactory(standaloneConfig, clientConfig)
        connectionFactory.afterPropertiesSet()   //设置 Bean 配置所有属性后再进行初始化
        return connectionFactory
    }

    /**
     * 配置连接客户端
     * @param outTime Long
     * @return JedisClientConfiguration
     *
     * JedisClientConfiguration类 ：对redis的客户端jedis的配置，可以通过设置提供的元素来实例化一个具体的JedisClientConfiguration对象
     */
    private fun jedisClientConfiguration(outTime:Long):JedisClientConfiguration{
        return JedisClientConfiguration
                .builder()
                .connectTimeout(Duration.ofMillis(outTime))
                .usePooling()
                .and()
                .readTimeout(Duration.ofMillis(outTime))
                .useSsl()
                .build()
    }

    /**
     * redis 连接地址信息配置
     * @param host String
     * @param port Int
     * @param database Int
     * @param password String?
     * @return JedisConnectionFactory
     *
     *  JedisConnectionFactory      用于设置 RedisConnection  通过RedisConnectionFactory 用来连接一个单点的redis
     *  类似的，可以使用下面两个类：
     *  RedisSentinelConfiguration  哨兵模式的配置
     *  RedisClusterConfiguration   集群配置
     */
    private fun redisStandaloneConfiguration(host: String, port: Int, database: Int, password: String?): RedisStandaloneConfiguration {
        val standaloneConfig = RedisStandaloneConfiguration()
        standaloneConfig.hostName = host
        standaloneConfig.port = port
        standaloneConfig.database = database
        password?.let { standaloneConfig.password = RedisPassword.of(password) }
        return standaloneConfig
    }
}