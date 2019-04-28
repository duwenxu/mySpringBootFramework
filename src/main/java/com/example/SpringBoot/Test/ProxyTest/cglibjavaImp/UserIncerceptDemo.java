package com.example.SpringBoot.Test.ProxyTest.cglibjavaImp;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 自定义的UserDao代理拦截器
 *
 * 拦截器。在调用目标方法时，CGLib会回调MethodInterceptor接口方法拦截，来实现你自己的代理逻辑，类似于JDK中的InvocationHandler接口
 * @author duwenxu
 * @create 2019-02-28 17:57
 */
public class UserIncerceptDemo implements MethodInterceptor {
    @Override
    /**
     * intercept方法的几个参数的含义为：
     *
     * Object表示要进行增强的对象
     * Method表示拦截的方法
     * Object[]数组表示参数列表，基本数据类型需要传入其包装类型，如int-->Integer、long-Long、double-->Double
     * MethodProxy表示对方法的代理，invokeSuper方法表示对被代理对象方法的调用
     */
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before...");
        Object object = methodProxy.invokeSuper(obj, objects);
        System.out.println("after...");
        return object;
    }
}
