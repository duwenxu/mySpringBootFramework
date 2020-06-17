package com.example.springboot.springsecurity.actualdemo

import org.springframework.security.crypto.password.PasswordEncoder

/**
 * 密码编码类   自定义密码的编码方式
 */
object DefinePasswordEncoder:PasswordEncoder{

    /**
     * 编码方法
     * @param rawPassword CharSequence?
     * @return String
     */
    override fun encode(rawPassword: CharSequence?): String {
        return rawPassword.toString()
    }

    /**
     * 匹配方法  验证编码前后是否匹配
     * @param rawPassword CharSequence?
     * @param encoderPassword String?
     * @return Boolean
     */
    override fun matches(rawPassword: CharSequence?, encoderPassword: String?): Boolean {
        return rawPassword.toString()==encoderPassword
    }
}