package com.example.SpringBoot.KotlinTest.ProxyTest.JDKProxyImp;

/**
 * UserDao
 *
 * @author duwenxu
 * @create 2019-02-28 17:55
 */
public class UserDao implements IUserDao {
    public void use(){
        System.out.println("开始使用...");
    }

    public void discard(){
        System.out.println("开始废弃...");
    }
}
