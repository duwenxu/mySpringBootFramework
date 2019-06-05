package com.waytogalaxy.display.common.config.jpa.extrepository

import org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT
import org.springframework.data.repository.core.RepositoryMetadata
import java.io.Serializable
import javax.persistence.EntityManager
import org.springframework.data.repository.core.RepositoryInformation
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

/**
 * jpa扩展 添加方法
 * @param T:Any
 * @param ID : Serializable
 * @property em EntityManager
 * @constructor
 */
class ExtJpaRepositoryFactory<T:Any, ID : Serializable>(private val em: EntityManager) : JpaRepositoryFactory(em) {


    /**
     * 获取目标repository
     * @param information RepositoryInformation
     * @param entityManager EntityManager
     * @return SimpleExtJpaRepository<*, *>
//     */
//    @Suppress("UNCHECKED_CAST")
//    override fun  <T, ID:Serializable>getTargetRepository(information: RepositoryInformation, entityManager: EntityManager): SimpleExtJpaRepository<*, *> {
//        val entityInformation = getEntityInformation<T,ID>(information.domainType as Class<T>)
//        return getTargetRepositoryViaReflection(information, entityInformation, entityManager)
//    }

    /**
     * 扩展jpa
     * @param metadata RepositoryMetadata
     * @return Class<*>
     */
    override fun getRepositoryBaseClass(metadata: RepositoryMetadata): Class<*> {
        return if (isQueryDslExecutor(metadata.repositoryInterface)) {
            QuerydslJpaRepository::class.java
        } else {
            SimpleExtJpaRepository::class.java
        }
    }

    /**
     * 扩展jpa
     * @param repositoryInterface Class<*>
     * @return Boolean
     */
    private fun isQueryDslExecutor(repositoryInterface: Class<*>): Boolean {
        return QUERY_DSL_PRESENT && QuerydslPredicateExecutor::class.java.isAssignableFrom(repositoryInterface)
    }
}