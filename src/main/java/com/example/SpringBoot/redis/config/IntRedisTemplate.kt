package com.example.springboot.redis.config

import org.springframework.data.redis.core.RedisTemplate

/**
 * 继承 RedisTemplate 来构建自己的 redisTemplate bean
 */
class IntRedisTemplate:RedisTemplate<Int,String>(){

}