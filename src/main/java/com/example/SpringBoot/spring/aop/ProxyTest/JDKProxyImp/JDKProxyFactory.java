package com.example.springboot.spring.aop.proxytest.JDKProxyImp;

import java.lang.reflect.Proxy;

/**
 * 生成JDK动态代理的代理对象
 *
 * @author duwenxu
 * @create 2019-02-28 18:21
 */
public class JDKProxyFactory {
    //需要代理的对象
    private Object object;

    public JDKProxyFactory(Object object) {
        this.object = object;
    }

    /**
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(JDKProxyFactory.class.getClassLoader(),
                object.getClass().getInterfaces(),
                (proxy, method, args) -> {  //new InvocationHandler(){}  lambda表达式实现
                    System.out.println("before....");
                    Object ObjectProxy = method.invoke(object, args);
                    System.out.println("after....");
                    return ObjectProxy;
                });
    }
}
