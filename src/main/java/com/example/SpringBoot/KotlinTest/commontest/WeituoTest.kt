package com.example.SpringBoot.KotlinTest.commontest

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * kotlin委托  在委托模式中，有两个对象参与处理同一个请求，接受请求的对象将请求委托给另一个对象来处理。
Kotlin 直接支持委托模式，更加优雅，简洁。Kotlin 通过关键字 by 实现委托
 */

//类委托：一个类中定义的方法实际是调用另一个类的对象的方法来实现的
/**
 * Demo：类委托
 */
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b

//委托者：Derived    被委托者：Base by b，Base接口的实现类

//属性委托：指的是一个类的某个属性值不是在类中直接进行定义，而是将其托付给一个代理类，从而实现对该类的属性统一管理。
/*
属性委托要求
对于只读属性(也就是说val属性), 它的委托必须提供一个名为getValue()的函数。该函数接受以下参数：
thisRef —— 必须与属性所有者类型（对于扩展属性——指被扩展的类型）相同或者是它的超类型  Any是所有类的超类
property —— 必须是类型 KProperty<*> 或其超类型
这个函数必须返回与属性相同的类型（或其子类型）。

对于一个值可变(mutable)属性(也就是说,var 属性),除 getValue()函数之外,它的委托还必须 另外再提供一个名为setValue()的函数, 这个函数接受以下参数:
property —— 必须是类型 KProperty<*> 或其超类型
new value —— 必须和属性同类型或者是它的超类型。
 */
/**
 * Demo：属性委托  属性委托对于val类属性需要提供getValue()方法，对于var属性需要提供getValue()和setValue()方法
 */
class Example {
    var p: String by Delegate()
    /**
     *  属性委托语法格式：
    val/var <属性名>: <类型> by <表达式>

    var/val：属性类型(可变/只读)
    属性名：属性名称
    类型：属性的数据类型
    表达式：委托代理的类
     */

}

class Delegate {
    operator fun getValue(beDelegate: Any?, prop: KProperty<*>): String {
        return "$beDelegate 在这里委托了${prop.name}属性"
    }

    operator fun setValue(beDelegate: Any?, prop: KProperty<*>, value: String) {
        println("将这里的$beDelegate 的${prop.name}属性赋值为 $value")
    }
}

/**
 * 标准委托：
 *      延迟属性（lazy properties）: 其值只在首次访问时计算，
 *      可观察属性（observable properties）: 监听器会收到有关此属性变更的通知，
 *      把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。
 */

/**
 * 延迟属性：lazy()是接受一个 lambda 并返回一个Lazy 实例的函数，返回的实例可以作为实现延迟属性的委托：
 *          第一次调用get()会执行已传递给lazy()的 lamda 表达式并记录结果， 后续调用get()只是返回记录的结果
 */
val lazyvalue: String by lazy {
    //    println("lambdaValue")
    val s = "lambdaValue"
    for (x in s) {
        println(x)
    }
    "lazyValue"  //第一次之后调用都只返回记录的结果
}

/**
 * 可观察属性 Observable
observable 可以用于实现观察者模式。
Delegates.observable() 函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)。
在属性赋值后会执行事件的响应器(handler)，它有三个参数：被赋值的属性、旧值和新值
 */
class ObservablePerson {
    var name: String by Delegates.observable("初始值") { propName, old, new ->  //相当于onChange()方法的三个参数，注意书写格式
        println("$propName 属性更改：$old--$new")
    }
}

/**
 * 把属性储存在映射中：
一个常见的用例是在一个映射（map）里存储属性的值。 这经常出现在像解析 JSON 或者做其他"动态"事情的应用中。 在这种情况下，你可以使用映射实例自身作为委托来实现委托属性
 */
class Site(var map: Map<String,Any>){
    val name:String by map
    val url:String by map
}

/**
 * Not Null
    notNull 适用于那些无法在初始化阶段就确定属性值的场合。
 */

fun main(args: Array<String>) {
    //类委托
//    val b = BaseImpl(10)
//    Derived(b).print() // prints 10

    //属性委托
//    var e=Example()
//    println(e.p)
//    e.p="myProperty"
//    println(e.p)

    //延迟属性委托
//    println(lazyvalue)
//    println("----------------------")
//    println(lazyvalue)
//    println("----------------------")
//    println(lazyvalue)

    //可观察属性委托
//    val person = ObservablePerson()
//    person.name="小明"
//    person.name="小华"

    //把属性储存在映射中
    val site= Site(mapOf(
            "name" to "1111",
            "url" to "2222"
    ))
    println(site.name)
    println(site.url)
}