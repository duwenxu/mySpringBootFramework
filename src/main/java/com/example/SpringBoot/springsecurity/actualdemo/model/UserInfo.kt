package com.example.springboot.springsecurity.actualdemo.model

import com.example.springboot.springsecurity.actualdemo.model.rbac_model.Role
import com.example.springboot.springsecurity.actualdemo.model.rbac_model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

/**
 * 自定义用户信息类
 * @property role Role?
 * @constructor
 */
class UserInfo(userInfo: User?,
               var role: Role?,
               var author: AuthorConf) : Serializable, UserDetails {

    companion object {
        private const val serialVersionUID = 1215454564L    //UID保证序列化前后 对象的一致性
    }

    var id: Int? = userInfo?.id
    private var username = userInfo?.name
    var authorCode = userInfo?.psword    //这里如果变量名定为password则在JVM标记层面相当于是 final fun getPassword()方法，这与UserDetails中的 open fun getPassword()冲突。 解决措施：1.将此处变量password声明为private 2.更换变量名
    var age = userInfo?.age
    var address = userInfo?.address
    var roleId = role?.id
    private var isEnabled = author.enabled
    private var isCredentialsNonExpired = author.credentialsNonExpired
    private var isAccountNonLocked = author.accountNonLocked
    private var isAccountNonExpired = author.accountNonExpired

    /**
     * 获取权限
     * @return MutableCollection<out GrantedAuthority>
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleId?.toString())
    }


    override fun getUsername() = username

    override fun getPassword() = authorCode

    /**
     * 是否可用
     * @return Boolean
     */
    override fun isEnabled() = isEnabled

    /**
     * 密码是否未过期
     * @return Boolean
     */
    override fun isCredentialsNonExpired() = isCredentialsNonExpired

    /**
     *  账户是否未过期
     * @return Boolean
     */
    override fun isAccountNonExpired() = isAccountNonExpired

    /**
     * 账户是否未锁定
     * @return Boolean
     */
    override fun isAccountNonLocked() = isAccountNonLocked
}