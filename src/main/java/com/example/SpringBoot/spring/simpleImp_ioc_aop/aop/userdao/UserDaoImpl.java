package com.example.springboot.spring.simpleimp_ioc_aop.aop.userdao;


/**
 * UserDaoImpl
 *
 * @author duwenxu
 * @create 2019-02-28 17:55
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public void use(){
        System.out.println("开始使用...");
    }

    @Override
    public void discard(){
        System.out.println("开始废弃...");
    }
}
