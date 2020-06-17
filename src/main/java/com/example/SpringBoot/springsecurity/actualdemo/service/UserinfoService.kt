package com.example.springboot.springsecurity.actualdemo.service

import com.example.springboot.jpa.repository.UserDao
import com.example.springboot.springsecurity.actualdemo.model.AuthorConf
import com.example.springboot.springsecurity.actualdemo.model.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

/**
 * userService类  返回UserInfo的对象实例
 * @property userdao UserDaoImpl
 */
@Component
class UserinfoService :UserDetailsService{

    @Autowired
    lateinit var userdao: UserDao

    /**
     * case sensitive：区分大小写
     * 根据 用户名
     * @param username String?
     * @return UserDetails
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        val userInfo = userdao.findByName(username)?:null
        val author= AuthorConf(accountNonExpired = true, accountNonLocked = true, credentialsNonExpired = true, enabled = true)
        return UserInfo(userInfo, null, author)
    }
}