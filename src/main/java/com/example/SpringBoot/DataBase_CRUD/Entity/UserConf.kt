package com.example.SpringBoot.DataBase_CRUD.Entity

import java.io.Serializable
import javax.persistence.*

/**
 * kotlin 数据库实体类对象
 * dataJPA 可配置自动建表
         *         1. :Serializable{
                companion object {
                private const val serialVersionUID = 1L
                }
            }
        实现Serializable接口：使能够序列化，对象可被序列化为二进制存储于硬盘中，减少了内存开销
        指定serialVersionUID：使序列化和反序列化过程中不会因为serialVersionUID不同而出现InvalidClassExceptions异常
    2.@Column 注解，可申明的属性有：
            name:定义了被标注字段在数据库表中所对应字段的名称   (默认为变量名)
            unique：否为唯一标识，默认为false
            nullable：表示该字段是否可以为null值，默认为true
            columnDefinition：用于@Entity自动生成表时指定字段类型   例：该类中指定text
 */
@Entity
class UserConf() : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    constructor(userId: Int?, info: String?, data: String?) : this() {
        this.userId = userId
        this.info = info
        this.data = data
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = Cons.ID_LENGTH)
    var id: Int? = null

    /**
     * 用户Id
     */
    @Column(length = Cons.ID_LENGTH)
    var userId: Int? = null

    /**
     * 配置信息
     */
    @Column(columnDefinition = "TEXT")
    var info: String? = null

    /**
     * 数据
     */
    @Column(columnDefinition = "TEXT")
    var data: String? = null
}


