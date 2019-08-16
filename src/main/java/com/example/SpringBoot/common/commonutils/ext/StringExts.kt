package com.example.springboot.common.commonutils.ext

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.google.gson.Gson
import java.math.BigDecimal
fun <T> String.toFastObj(clazz: Class<T>) =JSON.parseObject(this,clazz)   //ali JSON.parseObject(this,clazz)不能用于google的JsonObject类
fun <T> String.toObj(clazz: Class<T>) =Gson().fromJson(this,clazz)
fun <T> String.toObjList(clazz: Class<T>) = JSONArray.parseArray(this,clazz)



fun String.floatStrPointLength(length: Int, rounding: Int) = if (length in 1..29) {
    BigDecimal(this).setScale(length, rounding).toString()
} else {
    this
}

fun String.fillRangeString(range: Int): String {
    val ran = range - this.length
    return if (ran > 0) {
        val sb = StringBuffer(this)
        for (i in 0 until ran) {
            sb.insert(0, "0")
        }
        sb.toString()
    } else {
        this
    }
}


fun String.cutAt(range: Int):String {
    if(range > this.length){
        return this
    }else {
        return this.substring(0,range)
    }
}


fun String.cutOrFill(range: Int,fillTail:Boolean): String {
    var offset = this.length - range
    var str = this
    while (offset > 0) {
        str = str.substring(0, str.length - 1)
        offset --
    }
    while (offset < 0) {
        if(fillTail){
            str =  "${str}0"
        }else{
            str =  "0${str}"
        }

        offset ++
    }
    return str
}


