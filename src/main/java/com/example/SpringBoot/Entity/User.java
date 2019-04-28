package com.example.SpringBoot.Entity;

import javax.persistence.*;

/**
 * spring boot 实体类常用注解:
 * @Data 是 lombok 的注解，自动生成Getter，Setter，toString，构造函数等
 * @Table(name = "USER") 指定实体类对应的表名称
 * @Entity 注解这是个实体类
 * @Id 注解主键，@GeneratedValue 表示自动生成    @Id 标注用于声明一个实体类的属性映射为数据库的主键列
 * @GeneratedValue 用于标注主键的生成策略，通过strategy 属性指定，以下为集中可选策略：
 *          –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
 *          –AUTO： JPA自动选择合适的策略，是默认选项；
 *          –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
 *          –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
 * @DynamicUpdate，@DynamicInsert 注解可以动态的生成insert、update 语句，默认会生成全部的update
 * @Column 标识一些字段特性，字段别名，是否允许为空，是否唯一，是否进行插入和更新（比如由MySQL自动维护）
 * @Transient 标识该字段并非数据库字段映射
 */
@Entity //实体类
//@Table(name = "USER")
public class User{
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, unique = true)//标识字段不为空，唯一
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String address;

    public User() {
    }

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
