package com.example.springboot.database.jpaext.extrepository

import javax.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import java.io.Serializable

/**
 * 扩展jpaRepository
 * @param T:Any Entity类型
 * @param ID : Serializable id类型
 * @property em EntityManager
 */
class SimpleExtJpaRepository<T:Any, ID : Serializable> : SimpleJpaRepository<T, ID>, ExtJpaRepository<T, ID> {


    constructor(domainClass: Class<T>, em: EntityManager) : super(domainClass, em){
        this.em = em
    }

    constructor(entityInformation: JpaEntityInformation<T, *>, em: EntityManager) : super(entityInformation, em){
        this.em = em
    }

    private val em:EntityManager

    /**
     * 扩展的方法 如果属性为空 不处理 不为空修改或者更新
     * @param id ID
     * @param entity T
     */
    override fun saveSelective(id: ID, entity: T) {
        val managedEntity = this.findById(id).orElse(null)
        if (managedEntity == null) {
            em.persist(entity)
        } else {
            copyProperties(managedEntity, entity)
            save(entity)
        }
    }


    /**
     * 复制数据库中旧属性
     * @param orig T 源对象
     * @param dest T 目标对象
     */
    fun copyProperties(orig: T, dest: T) {

        val fields = orig::class.java.declaredFields
        var i = 0
        val len = fields.size
        while (i < len) {
            try {
                val field = fields[i]
                // 获取原来的访问控制权限
                val accessFlag = field.isAccessible
                // 修改访问控制权限
                field.isAccessible = true
                // 获取在对象f中属性fields[i]对应的对象中的变量
                val orignValue: Any? = field.get(orig)
                val destValue: Any? = field.get(dest)
                if (orignValue != null && destValue == null) {
                    field.set(dest, orignValue)
                }
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                field.isAccessible = accessFlag
            } catch (ex: IllegalArgumentException) {
                ex.printStackTrace()
            } catch (ex: IllegalAccessException) {
                ex.printStackTrace()
            }
            i++
        }
    }


}