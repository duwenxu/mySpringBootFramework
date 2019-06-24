package com.example.springboot.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/redis")
class RedisController{

    @Autowired
    val redisTemple=StringRedisTemplate()

    @RequestMapping("/setGet")
    fun easyOperator():String{
        redisTemple.opsForValue().set("key03","value01")
        return redisTemple.opsForValue().get("key01")
    }


}