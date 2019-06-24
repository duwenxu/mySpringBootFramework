package com.example.springboot.common.ftpUtil

/**
 * ftp配置类
 * @property ipAddr String
 * @property port Int?
 * @property username String
 * @property password String
 * @property filePath String?
 * @property fileName String
 * @constructor
 */

data class Ftp(
        val ipAddr: String,
        val port: Int?,
        val username:String,
        val password:String,
        var filePath:String?,  //下载文件的路径
        var fileName:String    //下载文件的文件名
)


