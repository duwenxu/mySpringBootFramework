package com.example.springboot.database.repository

import com.example.springboot.springsecurity.actualdemo.model.rbac_model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface RoleRepository:JpaRepository<Role,Int>{
}