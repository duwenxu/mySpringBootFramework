package com.example.SpringBoot.jpa.extrepository

import com.waytogalaxy.display.common.config.jpa.extrepository.ExtJpaRepositoryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean
import org.springframework.data.repository.core.support.RepositoryFactorySupport
import java.io.Serializable
import javax.persistence.EntityManager

/**
 * 以下都用于扩展jpa方法
 * @param R:JpaRepository<T,ID>
 * @param T:Any
 * @param ID:Serializable
 * @constructor
 */
class  ExtJpaRepositoryFactoryBean<R:JpaRepository<T,ID>,T:Any , ID:Serializable>(repositoryInterfaces: Class<R>) : JpaRepositoryFactoryBean<R,T,ID>(repositoryInterfaces) {

    /**
     * 替换原有的reposotoryFactorySupport 改为扩展的
     * @param entityManager EntityManager
     * @return RepositoryFactorySupport
     */
    override fun createRepositoryFactory(entityManager: EntityManager): RepositoryFactorySupport {
        return ExtJpaRepositoryFactory<T,ID>(entityManager)
    }



}