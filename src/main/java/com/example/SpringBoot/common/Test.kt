package com.example.springboot.common

abstract class Person{
    abstract fun eat()
}


class Student:Person(){
    override fun eat() {
        println("吃饭")
    }
}

fun main(args: Array<String>) {
    val student = Student()
    if (student is Person){
        student.eat()
    }
}