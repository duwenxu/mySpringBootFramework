package com.example.SpringBoot.Test.ProxyTest.JDKProxyImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before....");
                        Object ObjectProxy = method.invoke(object, args);
                        System.out.println("after....");
                        return ObjectProxy;
                    }
                });
    }
}
