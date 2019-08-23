package com.example.springboot.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/redis")
class RedisController{

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    @RequestMapping("/setGet")
    fun easyOperator():String{
        redisTemplate.opsForValue().set("key03","value01")
        return redisTemplate.opsForValue().get("key01")
    }


}