package com.waytogalaxy.display.utils.ftpUtil

data class Ftp(
        val ipAddr: String,
        val port: Int,
        val username:String,
        val password:String,
        var filePath:String?,
        var fileName:String
)