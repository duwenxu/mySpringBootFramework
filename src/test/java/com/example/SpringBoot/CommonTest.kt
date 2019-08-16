package com.example.SpringBoot

import com.example.springboot.database.model.Role
import com.example.springboot.database.repository.RoleRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class CommonTest {

    companion object {
        @Autowired
        lateinit var roleRepository: RoleRepository
    }


    @Test
    fun test1() {
        val spacecraft_ = 2684526593
        println("${spacecraft_.toString(16)}")   //a002a001
        val list = listOf<String>()
        println(list.size)
    }

    @Test
    fun addTest() {
        val role1 = Role()
        role1.descriptor = "管理员"
        role1.roleName = "admin"
        role1.updateBy = 1
        role1.updateTime = Date()
        val role2 = role1
        role2.roleName = "superUser"
        role2.descriptor = "超级用户"
        roleRepository.save(role1)
        roleRepository.save(role2)
    }
}