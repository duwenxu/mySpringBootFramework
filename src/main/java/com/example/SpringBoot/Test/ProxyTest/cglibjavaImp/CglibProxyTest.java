package com.example.SpringBoot.Test.ProxyTest.cglibjavaImp;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * CglibProxyTest
 *
 * @author duwenxu
 * @create 2019-02-28 18:07
 */
public class CglibProxyTest {

    /**
     * 生成动态代理对象
     * @return
     */
    public static Object proxyFactory(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        enhancer.setCallback(new UserIncerceptDemo());
        Object object = enhancer.create();
        return object;
    }

    @Test
    public void test(){
        UserDao Cglibproxy = (UserDao) CglibProxyTest.proxyFactory();
        Cglibproxy.use();
        Cglibproxy.discard();
    }
}
