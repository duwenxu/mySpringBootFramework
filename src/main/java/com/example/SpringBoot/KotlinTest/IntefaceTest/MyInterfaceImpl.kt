package com.example.springboot.KotlinTest.IntefaceTest

import com.example.SpringBoot.KotlinTest.IntefaceTest.MyInterface

class MyInterfaceImpl(
        val name:String,
        val age:Int,
        val address: String
) : MyInterface {
    override fun toString(): String {
        return "MyInterfaceImpl(name='$name', age=$age, address='$address')"
    }
}