package com.example.SpringBoot.KotlinTest.commontest

import com.example.SpringBoot.common.Utils.TimeUtils.Companion.Y_S_CLOSE
import com.google.gson.Gson
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    /**
     * 循环控制语句等
     */
    var x=1;
    when(x){
        1 -> println("$x+2")
        2 -> println("$x+1")
    }

    var a = "abccdacvfr"
    for (x in a.indices) {
        println(a[x])
    }
//
//    println("------------------------------")
//
//    for (x in a) {
//        println("用字符串模板输出$x")
//    }
//
//    println("------------------------------")
//
//    for (x in a.indices) {
//        println("the element of the index$x is ${a[x]}")
//    }
//
//    println("------------------------------")
//
//    for ((index, value) in a.withIndex()) {
//        println("$index----$value")
//    }
    /**
     * kotlin标签使用  三种跳转表达式
     */
//    //lable标签
//    mylable@ for (x in 20 downTo 12 step 2 ){
//        shut@for (y in 12..20){
//            if (x==y){
//                println("$x")
//            }else{
//                if (x==16)break@shut
//            }
//        }
//    }

    //
//   // 1.从外层函数返回：
//    fun foo1() {
//        list.forEach {
//            if (it == 0) return // 默认从foo(){}返回
//            print(it)
//        }
//    }
//
//    //2.用显式标签从lambda表达式中返回:
//    fun foo2() {
//        list.forEach lit@ {
//            if (it == 0) return@lit // 标记从forEach{}返回
//            print(it)
//        }
//    }
//
//   // 3.用隐式标签(与接收lambda的函数同名)从lambda表达式中返回:s
//    fun foo3() {
//        list.forEach {
//            if (it == 0) return@forEach // 隐式标签forEach，从forEach{}返回
//            print(it)
//        }
//    }
//
//    //4.用匿名函数替代lambda表达式:
//    fun foo4() {
//        list.forEach(fun(value: Int) {
//            if (value == 0) return // 从该匿名函数fun返回
//            print(value)
//        })
//    }

    /**
     * list中的各种创建以及遍历方式
     *
     */
    //创建不可变列表
    val myList: List<Int> = listOf()   //定义一个空列表
    val list: List<Int> = listOf(0, 2, 3, 4, 8, 9)   //注意：等号之前的空格不可省略
    //创建可变列表
    var list2 = mutableListOf<Int>()
    var list1 = mutableListOf(1, 2, 5, 8, 4, 7).toList()
    var list3= mutableListOf(1)
//    println(list2)
//    list1.reverse()
//
//    println(list2)
    list1 = list1.sortedByDescending { it }   //倒序排列
    println(list1)
//    //不可变转可变
//    val myMutableList = list.toMutableList()
//    println(myMutableList)
//    myMutableList.add(0, 12)
//    println(myMutableList)

//    //迭代器遍历
//    val iterator = list1.iterator()
//    while (iterator.hasNext()) {
//        println(iterator.next())
//    }

//    //直接遍历
//    for (i in list) {
//        println(i)
//    }

    //下标遍历
    for (i in list.indices) {
        println(list[i])
    }

//    //withIndex()函数遍历List元素
//    for ((a, b) in list.withIndex()) {
//        println("下标：$a 数值：$b")
//    }
//
//    //使用forEach遍历List元素
//    list.forEach() {
//        println(it)
//    }
//
//    //带步长逆向遍历 （只能遍历数据？）
//    for (i in list.size downTo 0 step 2) {
//        println(i)
//    }

    /**
     * Kotlin中的Map分为：
     *   只读Map。
     *   可变的MutableMap(MutableMap、HashMap、LinkedHashMap)。
     */
//    val p1= TestEntitr("liming",23)
//    val p2= TestEntitr("xiaoxiao",12)
//    //创建只读map
//    val mMap= mapOf(0 to p1,1 to p1,2 to p2,3 to p2)
//    println(mMap[0])
//    val map1=mapOf(123 to p1,345 to "sec",444 to "hhh")
//    val maplist= mutableListOf(mMap,map1)
//
//    println(Gson().toJson(maplist))
//    val String="caaaaab"
//    println(String.substring(1,String.length-1))
//
//    val time="20190327190537"
//    println(time.dateToStamp(Y_S_CLOSE))

}


fun String.dateToStamp(format:String):Long{
    return SimpleDateFormat(format).parse(this).time.toString().toLong()
}

