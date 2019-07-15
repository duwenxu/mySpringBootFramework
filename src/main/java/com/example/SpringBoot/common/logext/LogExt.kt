package com.example.springboot.common.logext

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LogExt {
    val logger: Logger = LoggerFactory.getLogger(javaClass)
}

fun <T> toInfo(clazz: Class<T>,msg:String){
    LoggerFactory.getLogger(clazz).info(msg)
}

fun <T> toError(clazz: Class<T>,msg:String){
    LoggerFactory.getLogger(clazz).error(msg)
}

fun toInfo(msg:String){
    LogExt.logger.info(msg)
}

fun toError(msg: String){
    LogExt.logger.error(msg)
}

fun toWarn(msg: String){
    LogExt.logger.warn(msg)
}

fun toDebug(msg: String){
    LogExt.logger.debug(msg)
}
