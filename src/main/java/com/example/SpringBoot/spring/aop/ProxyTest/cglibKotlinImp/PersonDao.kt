package com.example.springboot.spring.aop.proxytest.cglibKotlinImp

/**
 * personDao
 *
 * @author duwenxu
 * @create 2019-02-28 14:50
 */
open class PersonDao {

    fun eat() {
        println("开始吃饭....")
    }

    fun sleep() {
        println("开始睡觉....")
    }
}