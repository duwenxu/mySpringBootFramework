package com.example.springboot.utils

import com.google.gson.Gson

object GsonUtil{

    val gson = Gson()

    fun <T> fromJson(str:String?,clazz: Class<T>) = if(str == null){
        null
    }else{
        gson.fromJson(str,clazz)
    }

}