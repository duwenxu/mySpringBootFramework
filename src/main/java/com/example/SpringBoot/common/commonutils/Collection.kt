package com.example.springboot.common.commonutils

fun <T> Collection<T>.toArrayList():ArrayList<T> {
    val arrayList = ArrayList<T>()
    arrayList.addAll(this)
    return arrayList
}