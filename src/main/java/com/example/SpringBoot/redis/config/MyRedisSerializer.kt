package com.example.springboot.redis.config

import com.example.springboot.common.commonutils.ext.toJson
import com.google.gson.GsonBuilder
import com.sun.org.apache.xml.internal.security.signature.ObjectContainer
import org.springframework.data.redis.serializer.RedisSerializer

/**
 * redis 序列化类的实现
 * RedisSerializer 类：主要建议实现对于 空对象/空数组 的序列化和反序列化处理
 */
class MyRedisSerializer : RedisSerializer<Any> {

    /**
     * Gson 的使用 ：序列化和反序列化的方法
     */
    private val gson = GsonBuilder().serializeNulls().create()

    /**
     * 序列化
     * @param t Any?
     * @return ByteArray
     */
    override fun serialize(t: Any?) = if (t == null) {
        byteArrayOf(0)
    } else {
        val data: Any = t
        gson.toJson(ObjContainer(data::class.java.name, t.toJson())).toByteArray()
    }

    /**
     * 反序列化
     * @param bytes ByteArray?
     * @return Nothing
     */
    override fun deserialize(bytes: ByteArray?) = if (bytes == null) {
        null
    } else {
        val container = gson.fromJson(String(bytes), ObjContainer::class.java)
        val clazz = Class.forName(container.className)
        gson.fromJson(container.jsonData, clazz)
    }
}


