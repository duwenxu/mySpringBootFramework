package com.example.SpringBoot.common.ftpUtil.result

class UpLoadResult(val successed: Boolean, val absPath: String?, val fileName: String?, val msg: String?)

class MethodResult<T>(val successed: Boolean, val t: T, val msg: String)