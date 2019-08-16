package com.example.springboot.DataBase_CRUD.Entity

import lombok.Data
import java.io.Serializable
import javax.persistence.*

/**
 * spring boot 实体类常用注解:
 * @Data 是 lombok 的注解，自动生成Getter，Setter，toString，构造函数等
 * @Table(name = "USER") 指定实体类对应的表名称
 * @Entity 注解这是个实体类
 * @Id 注解主键，@GeneratedValue 表示自动生成    @Id 标注用于声明一个实体类的属性映射为数据库的主键列
 * @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定，以下为集中可选策略：
 * –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
 * –AUTO： JPA自动选择合适的策略，是默认选项；
 * –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
 * –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
 * @DynamicUpdate，@DynamicInsert 注解可以动态的生成insert、update 语句，默认会生成全部的update
 * @Column 标识一些字段特性，字段别名，是否允许为空，是否唯一，是否进行插入和更新（比如由MySQL自动维护）
 * @Transient 标识该字段并非数据库字段映射
 */
@Entity
@Data
//@Table(name = "USER")
class User() : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }

    @Id
    @GeneratedValue
    var id: Int = 0

    @Column(nullable = false, unique = true)//标识字段不为空，唯一
    var name: String? = null

    @Column(nullable = false)
    var age: Int? = 0

    @Column(nullable = false)
    var address: String? = null

    constructor(
            name: String?,
            age: Int?,
            address: String?
    ) : this()
}
