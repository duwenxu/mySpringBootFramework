//package com.example.springboot.springsecurity.user
//
//import com.example.springboot.DataBase_CRUD.Entity.User
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.AuthorityUtils
//import org.springframework.security.core.userdetails.UserDetails
//import java.io.Serializable
//
//class UserInfo (userInfo: User,
//                var role:Role?): Serializable,UserDetails{
//
//    companion object {
//        private const val serialVersionUID=1215454564L    //UID保证序列化前后 对象的一致性
//    }
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//       return AuthorityUtils.commaSeparatedStringToAuthorityList(role)
//    }
//
//    override fun isEnabled(): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getUsername(): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getPassword(): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}