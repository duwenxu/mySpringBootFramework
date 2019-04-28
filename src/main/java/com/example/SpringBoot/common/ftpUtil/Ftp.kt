package com.example.SpringBoot.common.ftpUtil

data class Ftp(
        val ipAddr: String,
        val port: Int,
        val username:String,
        val password:String,
        var filePath:String?,
        var fileName:String
)