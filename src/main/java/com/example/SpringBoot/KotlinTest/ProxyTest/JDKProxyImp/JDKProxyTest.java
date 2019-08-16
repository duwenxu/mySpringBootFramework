package com.example.SpringBoot.KotlinTest.ProxyTest.JDKProxyImp;

import org.junit.Test;

/**
 * JDKProxyFactory
 *
 * @author duwenxu
 * @create 2019-02-28 18:27
 */
public class JDKProxyTest {
    @Test
    public void test(){
        IUserDao userDao = new UserDao();//创建目标对象作为代理工厂的传入参数

        //创建代理对象
        IUserDao JDKProxy = (IUserDao) new JDKProxyFactory(userDao).getProxyInstance();
        JDKProxy.discard();
        JDKProxy.use();
    }

}
