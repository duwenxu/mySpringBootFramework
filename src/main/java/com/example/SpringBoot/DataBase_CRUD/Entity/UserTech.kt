package com.example.SpringBoot.DataBase_CRUD.Entity

//Kotlin 可以创建一个只包含数据的类，关键字为 data：
//data class User(val name: String, val age: Int)
/**
 *编译器会自动的从主构造函数中根据所有声明的属性提取以下函数：
equals() / hashCode()
toString() 格式如 "User(name=John, age=42)"
componentN() functions 对应于属性，按声明顺序排列
copy() 函数
如果这些函数在类中已经被明确定义了，或者从超类中继承而来，就不再会生成。
为了保证生成代码的一致性以及有意义，数据类需要满足以下条件：
主构造函数至少包含一个参数。
所有的主构造函数的参数必须标识为val 或者 var ;
数据类不可以声明为 abstract, open, sealed 或者 inner;
数据类不能继承其他类 (但是可以实现接口)。
 */
data class UserTech(var id: Int, var name: String, var tech1: String, var tech2: String, var tech3: String, var base: String)