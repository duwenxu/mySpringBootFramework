package com.example.SpringBoot.redis

import com.example.SpringBoot.common.Cons
import com.example.SpringBoot.common.Utils.toArrayList
import com.example.SpringBoot.common.Utils.toJson
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit

class RedisExt


fun <T : Any> RedisTemplate<String, T>.just(key: String, value: T) {
    this.opsForValue()[key] = value
}


fun <T : Any> RedisTemplate<String, T>.just(key: String, value: T, time: Long, unit: TimeUnit) {
    this.opsForValue().set(key, value, time, unit)
}

/**
 * 获取 value
 */
fun <T : Any> RedisTemplate<String, T>.get(key: String) = this.opsForValue().get(key)

/**
 * 匹配获取key
 */
fun <T : Any> RedisTemplate<String, T>.match(rex: String): ArrayList<T> {
    val keys = this.keys("$rex*")
    return keys.mapNotNull { this.get(it) }.toArrayList()
}

fun <T : Any> RedisTemplate<String, T>.matchList(rex: String): ArrayList<T>? {
    return this.opsForList().range("list:$rex", 0, -1)?.distinct()?.toArrayList()
}


fun <T : Any> RedisTemplate<String, T>.getLast(key: String) = this.opsForZSet().reverseRange(key, 0, -1)?.firstOrNull()

fun <T : Any> RedisTemplate<String, T>.scopeCaches(key: String, scope: Long) = this.opsForZSet().reverseRange(key, 0, scope - 1)


fun <T : Any> RedisTemplate<String, T>.matchLast(rex: String): List<T> {
    val keys = this.keys("$rex*")
    return keys.mapNotNull { this.opsForZSet().reverseRange(it, 0, -1)?.firstOrNull() }
}
fun <T : Any> RedisTemplate<String, T>.putMap(key: String, mapKey: String,value: T) {
    this.opsForHash<String,T>().put(key,mapKey,value)
}

fun <T : Any> RedisTemplate<String, T>.getMap(key: String):Map<String,T> {
    return this.opsForHash<String,T>().entries(key)
}

fun <T : Any> RedisTemplate<String, T>.leftPush(key: String, value: T) = this.opsForList().leftPush(key, value)

fun <T : Any> RedisTemplate<String, T>.fromList(key: String, start: Long, end: Long) = this.opsForList().range(key, start, end)


/**
 * 缓存数据
 */
fun <T : Any> RedisTemplate<String, String>.cacheObj2Str(key: String, value: T, transform: (T) -> Double) {
    this.opsForZSet().add(key, value.toJson(), transform(value))
    this.expire(key, 5, TimeUnit.DAYS)
}


/**
 * 匹配获取key
 */
fun <T : Any> RedisTemplate<String, T>.deletePre(rex: String) {
    val keys = this.keys("$rex*")
    this.delete(keys)
}

/**
 * 缓存数据
 */
fun <T : Any> RedisTemplate<String, T>.cacheZset(key: String, value: T) {
    this.opsForZSet().add(key, value, System.currentTimeMillis().toDouble())
}

fun <T : Any> RedisTemplate<String, T>.cacheList(key: String, value: T) {
    this.opsForList().leftPush("list:$key", value)
}

fun <T : Any> RedisTemplate<String, T>.listSet(key: String, index: Long, value: T) {
    this.opsForList().set(key, index, value)
}

/**
 * 获取缓存数据
 */
fun <T : Any> RedisTemplate<String, T>.fromCache(key: String): MutableSet<T>? {
    val currentTime = System.currentTimeMillis()
    removeOutData(key, currentTime)
    return this.opsForZSet().reverseRangeByScore(key, currentTime - Cons.TIME_DAY.toDouble(), currentTime.toDouble())?.toMutableSet()
}


fun <T : Any> RedisTemplate<String, T>.fromCache(key: String, num: Int): List<T>? {
    val currentTime = System.currentTimeMillis()
    removeOutData(key, currentTime)
    return this.opsForZSet().range(key, 0, (num - 1).toLong())?.toList()
}

private fun <T : Any> RedisTemplate<String, T>.removeOutData(key: String, currentTime: Long) {
    this.opsForZSet().removeRangeByScore(key, 0.toDouble(), currentTime - Cons.TIME_DAY.toDouble())
}


fun <T : Any> RedisTemplate<String, T>.put(key: String, value: T) {
    this.opsForSet().add(key, value)
}

fun <T : Any> RedisTemplate<String, T>.deleteItem(key: String, value: T) {
    this.opsForSet().remove(key, value)
}

fun <T : Any> RedisTemplate<String, T>.getAll(key: String): Set<T>? {
    return this.opsForSet().members(key)
}






