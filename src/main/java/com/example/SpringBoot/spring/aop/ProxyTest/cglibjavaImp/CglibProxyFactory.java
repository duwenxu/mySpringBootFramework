package com.example.springboot.spring.aop.proxytest.cglibjavaImp;

import org.springframework.cglib.proxy.Enhancer;

/**
 * cglib代理类获取工场
 *
 * @author duwenxu
 * @create 2021-01-13 15:25
 */
public class CglibProxyFactory {
    /**
     * 生成动态代理对象
     * @return
     */
    public static Object getProxy(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(clazz.getClassLoader());
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new UserdaoInterceptor());
        return enhancer.create();
    }
}
