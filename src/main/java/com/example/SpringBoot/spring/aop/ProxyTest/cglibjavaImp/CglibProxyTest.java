package com.example.springboot.spring.aop.proxytest.cglibjavaImp;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * CglibProxyTest
 *
 * Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
 * JDK的动态代理有一个限制,就是使用动态代理的对象必须实现一个或多个接口,如果想代理没有实现接口的类,就可以使用Cglib实现.
 *
 * @author duwenxu
 * @create 2019-02-28 18:07
 */
public class CglibProxyTest {

    @Test
    public void test(){
        UserDao Cglibproxy = (UserDao) CglibProxyFactory.getProxy(UserDao.class);
        Cglibproxy.use();
        Cglibproxy.discard();
    }
}
