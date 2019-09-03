package com.example.springboot.spring.simpleImp_ioc_aop.aop;

import com.example.springboot.spring.simpleImp_ioc_aop.aop.advice.Advice;

import java.lang.reflect.Proxy;

/**
 * AOP 获取代理对象实例
 *
 * @author duwenxu
 * @create 2019-09-02 14:32
 */
public class SimpleAOP {
    public static Object getProxy(Object bean,Advice advice){
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }
}
