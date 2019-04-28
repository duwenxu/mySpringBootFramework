package com.example.SpringBoot.common.Utils

fun <T> Collection<T>.toArrayList():ArrayList<T> {
    val arrayList = ArrayList<T>()
    arrayList.addAll(this)
    return arrayList
}