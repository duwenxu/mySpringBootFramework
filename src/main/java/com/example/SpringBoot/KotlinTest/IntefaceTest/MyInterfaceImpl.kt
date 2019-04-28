package com.example.SpringBoot.KotlinTest.IntefaceTest

import java.net.Inet4Address

class MyInterfaceImpl(
        val name:String,
        val age:Int,
        val address: String
) : MyInterface{
    override fun toString(): String {
        return "MyInterfaceImpl(name='$name', age=$age, address='$address')"
    }
}