package com.example.SpringBoot.redis

import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

/**
 *RedisTemplate中定义了5种数据结构操作
redisTemplate.opsForValue();　　//操作字符串
redisTemplate.opsForHash();　　 //操作hash
redisTemplate.opsForList();　　 //操作list
redisTemplate.opsForSet();　　  //操作set
redisTemplate.opsForZSet();　 　//操作有序set
 */
class RedisExt {

    /***
     * @Description:String操作
     * @Params:  * @param null :
     * @Return: * @return : null
     */
//设置值
    fun <T : Any> RedisTemplate<String, T>.justSet(key: String, value: T) {
        this.opsForValue()[key] = value
    }

    fun <T : Any> RedisTemplate<String, T>.Set(key: String, value: T, outTime: Long, unit: TimeUnit) {
        this.opsForValue().set(key, value, outTime, unit)
    }

    //获取值
    fun <T : Any> RedisTemplate<String, T>.get(key: String) {
        this.opsForValue().get(key)
    }

}