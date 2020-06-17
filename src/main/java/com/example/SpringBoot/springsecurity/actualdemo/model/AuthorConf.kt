package com.example.springboot.springsecurity.actualdemo.model

import java.io.Serializable

/**
 * 用户权限配置
 * @property accountNonExpired Boolean
 * @property accountNonLocked Boolean
 * @property credentialsNonExpired Boolean
 * @property enabled Boolean
 * @constructor
 *
 * 用户名、密码、权限以及四个状态，状态默认为true，其中任何一个为false，都将导致登录失败
 * 所以其实可以默认的设置为全 true
 */
data class AuthorConf(
        var accountNonExpired: Boolean,      //用户账户是否 未 过期
        var accountNonLocked: Boolean,       //用户账户是否 未 锁定
        var credentialsNonExpired: Boolean,  // 用户的验权是否 未 过期
        var enabled: Boolean                 //是否可用
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }
}