package com.example.springboot.springsecurity.model.rbac_model

import java.io.Serializable
import java.util.*
import javax.persistence.*

/**
 * 定义用户权限
 * RBAC  用户角色
 * @property id Int?
 * @property descriptor String?
 * @property roleName String?
 * @property updateTime Date?
 * @property updateBy Int?
 * @constructor
 */
@Entity
data class Role(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(length = Cons.ID_LENGTH)
        var id: Int? = null,

        @Column(length = Cons.FILE_NAME_LENGTH)
        var descriptor: String? = null,

        var roleName: String? = null,
        var updateTime: Date? = null,
        var updateBy: Int? = null

) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }
}