package com.example.SpringBoot.common.ftpUtil



data class Ftp(
        val ipAddr: String,
        val port: Int,
        val username:String,
        val password:String,
        var filePath:String?,  //下载文件的路径
        var fileName:String    //下载文件的文件名
)

data class UploadFtp(
        val ipAddr: String,
        val port: Int,
        val username:String,
        val password:String,
        var filePath:String?    //要上传到的路径
)


