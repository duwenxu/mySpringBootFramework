package com.example.SpringBoot.KotlinTest.commontest


fun main(args: Array<String>) {
    var list1 = mutableListOf(1, 2, 4, 8, 9, 7,13).toList()
    println(list1.sortedBy { it })
}
