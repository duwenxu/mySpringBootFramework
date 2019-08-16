package com.example.springboot.springsecurity.user

import java.io.Serializable
import java.util.*

/**
 * 定义用户权限
 * @property id Int?
 * @property descriptor String?
 * @property roleName String?
 * @property updateTime Date?
 * @property updateBy Int?
 * @constructor
 */
data class Role(
        var id:Int?=null,
        var descriptor: String?=null,
        var roleName:String?=null,
        var updateTime:Date?=null,
        var updateBy:Int?=null
):Serializable{
    companion object {
        const val serialVersionUID=1L
    }
}