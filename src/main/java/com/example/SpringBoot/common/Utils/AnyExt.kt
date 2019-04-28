package com.example.SpringBoot.common.Utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlin.reflect.KClass




fun Any.toMap(): HashMap<String, Any?> {
    val map = HashMap<String, Any?>()
    // 获取f对象对应类中的所有属性域
    val fields = this::class.java.declaredFields
    var i = 0
    val len = fields.size
    while (i < len) {
        val varName = fields[i].name
        try {
            // 获取原来的访问控制权限
            val accessFlag = fields[i].isAccessible
            // 修改访问控制权限
            fields[i].isAccessible = true
            // 获取在对象f中属性fields[i]对应的对象中的变量
            val o: Any? = fields[i].get(this)
//            if(o != null && !o.isBaseType()){
//                map[varName] = o.toMap()
//            }else {
            map[varName] = o
//            }
            // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
            // 恢复访问控制权限
            fields[i].isAccessible = accessFlag
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        } catch (ex: IllegalAccessException) {
            ex.printStackTrace()
        }
        i++
    }
    return map
}




fun Any.toJson() = Gson().toJson(this)
fun Any.toFastJson()=JSON.toJSONString(this)

fun Any.toSerialNullJson()=GsonBuilder().serializeNulls().create().toJson(this)


fun Any.toJsonObj() = Gson().toJsonTree(this).asJsonObject
fun Any.toFastJsonObj() = JSONObject.parseObject(JSON.toJSONString(this))
/**
 * 保留空值的Json序列化方式
 */
fun Any.toSerialNullJsonObj()=GsonBuilder().serializeNulls().create().toJsonTree(this).asJsonObject


fun Any.toJsonArray() = Gson().toJsonTree(this).asJsonArray




class ConvertHolder<T : Any>(val fir: Any, val clazz: KClass<T>) {

    val firMap = fir.toMap()
    val secMap = HashMap<String, Any?>()
}









