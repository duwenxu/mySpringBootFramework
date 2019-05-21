package com.example.SpringBoot.KotlinTest.commontest

import com.example.SpringBoot.common.Utils.toArrayList
import java.io.File
import java.util.*

object KotlinIoTest{

    val synchronizedList = Collections.synchronizedList(ArrayList<String>()).toArrayList()
    fun printContext(path:String){
        File(path).readLines().forEach(::println)
    }



}

fun main(args: Array<String>) {
    KotlinIoTest.printContext("D:\\开发相关\\网管\\中心工作计划\\frame-v068.proto")

}