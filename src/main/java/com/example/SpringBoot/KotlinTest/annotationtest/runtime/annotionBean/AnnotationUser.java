package com.example.springboot.KotlinTest.annotationtest.runtime.annotionBean;

import com.example.springboot.KotlinTest.annotationtest.runtime.annotations.Contraints;
import com.example.springboot.KotlinTest.annotationtest.runtime.annotations.DBtable;
import com.example.springboot.KotlinTest.annotationtest.runtime.annotations.SqlInteger;
import com.example.springboot.KotlinTest.annotationtest.runtime.annotations.SqlString;

/**
 * 使用自定义注解的标注Bean
 *
 * @author duwenxu
 * @create 2019-04-29 10:50
 */

@DBtable(name = "CODER")
public class AnnotationUser {

    @SqlInteger(name = "id",contraints = @Contraints(isPrimaryKey = true))
    private int id;

    @SqlString(name = "name",length = 20)
    private String name;

    @SqlInteger(name = "age")
    private int age;

    @SqlString(name = "address",length = 50)
    private String address;

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
        return "AnnotationUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
