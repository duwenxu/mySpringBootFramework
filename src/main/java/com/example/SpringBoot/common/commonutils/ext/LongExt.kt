package com.example.springboot.common.commonutils.ext

import java.text.SimpleDateFormat

fun Long.stampToDate(format:String):String{
    return SimpleDateFormat(format).format(this)
}