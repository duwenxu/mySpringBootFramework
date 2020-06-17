package com.example.springboot.springsecurity.actualdemo.model.rbac_model

import javax.persistence.*

/**
 * RBAC  可访问的资源表
 * @property id Int
 * @property url String
 * @property description String
 * @property updateBy String
 * @property updateTime String
 * @constructor
 */
@Entity
data class Source(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,

        @Column(length = Cons.URL_LENGTH)
        var url: String,

        @Column(length = Cons.FILE_NAME_LENGTH)
        var description: String,

        @Column(length = Cons.ID_LENGTH)
        var updateBy: String,

        @Column(length = Cons.FILE_NAME_LENGTH)
        var updateTime: String
)