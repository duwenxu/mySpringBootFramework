package com.example.springboot.spring.aop.proxytest.cglibjavaImp;

/**
 * UserDaoImpl
 *
 * @author duwenxu
 * @create 2019-02-28 17:55
 */
public class UserDao {
    public void use(){
        System.out.println("开始使用...");
    }

    public void discard(){
        System.out.println("开始废弃...");
    }
}
