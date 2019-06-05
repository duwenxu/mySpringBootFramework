package com.waytogalaxy.display.common.config.jpa.extrepository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

/**
 * jpa方法扩展
 */
@NoRepositoryBean
interface ExtJpaRepository<T, ID : Serializable> : JpaRepository<T, ID> {

    /**
     * 保存有值的字段  不处理无值的字段
     * @param id ID
     * @param entity T
     */
    fun saveSelective(id: ID, entity: T)

}