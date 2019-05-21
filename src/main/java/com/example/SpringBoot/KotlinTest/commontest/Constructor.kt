package com.example.SpringBoot.KotlinTest.commontest


//kotlin中的主构造器和次构造器
class Test(name: String) {
    var webname = name
    var url: String = "http://baidu.com"
    var country: String = "CN"

    //初始化代码块
    init {
        println("$name")
    }

    fun myFun() {
        println("这是网站${url}的函数")
    }
}

//直接在Primary Constructor中定义类的属性。
class Test1(private var name: String, private var age: Int, private var address: String)

//次构造器 Secondary Constructor
//1.Secondary Constructor是定义在类体中
//2.Secondary Constructor可以有多个，而Primary Constructor只会有一个。
//继承框架中的类时需要重写多个构造器，因此需要多个Secondary Constructor
//可以使用this关键字来调用自己的其他构造器，并且需要注意它的语法形式，次级构造器: this(参数列表)

class SecondTest constructor(name: String, age: Int) {
    //以var定义的变量自带get()和set()，以=的方式体现
    //以val定义的变量只有get()
    //可以用如下方式重写其get()和set()方法
    var name: String = name
        get() = field.toUpperCase()
    /**
     * 后端变量 field 默认定义在get和set方法中
     * 用于变量初始化重写get和set方法时
     */
    var age = age
    var address: String = "上海"
    val country: String = "CN"
    var hometown: String = "鲁"

    init {
        println("我是初始化函数！")
    }

    //Secondary Constructor
    constructor(name: String, age: Int, address: String) : this(name, age) {
        println("我是次级构造函数！参数列表：$name,$age,$address")
    }

    constructor(name: String, age: Int, address: String, hometown: String) : this(name, age, address) {
        println("我是次级构造函数！参数列表：$name,$age,$address,$hometown")
    }
}

//内部类
//要访问来自外部作用域的 this，我们使用this@label，其中@label是一个代指 this 来源的标签
class Outer {
    private val bar: Int = 1
    var v = "成员属性"
    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar  // 访问外部类成员
        fun innerTest():String {
            var o = this@Outer //获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v+o.bar)
            return o.v+o.bar
        }
    }
}

//继承
/**
 * 用户基类
 */
open class Person( name:String){
    constructor(name: String,age: Int) :this(name){
        println("基类次级构造函数")
    }
}
//1.带主构造函数子类， 基类必须在主构造函数中立即初始化。
class Student1(name: String,age: Int, address: String, no:Int): Person(name,age){}
//2.没有主构造函数的子类，则必须在每一个二级构造函数中用 super 关键字初始化基类，或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。
class Student2: Person {
    var name="李白"
    var age=33
    var school="长安"
    var level=25
    constructor(name: String,age: Int,school :String):super(name,age){
        println("子类次级构造函数 参数$name---$age")
    }
    constructor( name: String,age: Int,school: String,level :Int):this(name,age,school){
        println("子类次级构造函数,我是$name---$age")
    }
}

//重写
//在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词：
open class A {
    open fun f () { print("A") }
    fun a() { print("a") }
}

interface B {
    fun f() { print("B") } //kotlin中接口的成员变量默认是 open 的
    fun b() { print("b") } //kotlin接口中的方法可以被实现(区别于java)
}

open class C() : A() , B {  //同时继承类和实现接口
    open override  fun f() {
//        super<A>.f()//调用 A.f()
//        super<B>.f()//调用 B.f()
    }
}



fun main(args: Array<String>) {
//    val baidu=Test("百度")
//    baidu.myFun()
//    println(baidu.country+baidu.url+baidu.webname)
//
//    var test=Test1("jack",25,"HangKong")
//    println(test)

    //结论：次级构造会直接或者间接调用主构造
//    var constr1 = SecondTest("李白", 22, "长安")
//    println("-----------------------------------------")
//    var constr2 = SecondTest("李白", 22, "长安", "碎叶城")
//    constr1.name = "dufu"
//    println(constr1.name)

//    //内部类调用
//    var innerFoo=Outer().Inner().foo()
//    var innerElement=Outer().Inner().innerTest()
//    println("$innerElement"+"$innerFoo")
//    println("-----------------------")
//    println(Outer().Inner().innerTest())

//    var ts=Student2("李白",23,"北京",15)
//    println(ts.name+ts.age+ts.school+ts.level)

    val c = C()
    c.f()
}