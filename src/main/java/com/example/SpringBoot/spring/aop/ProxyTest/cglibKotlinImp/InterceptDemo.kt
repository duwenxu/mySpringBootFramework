package com.example.springboot.spring.aop.proxytest.cglibKotlinImp


import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy

import java.lang.reflect.Method

/**
 * 拦截器。在调用目标方法时，CGLib会回调MethodInterceptor接口方法拦截，来实现你自己的代理逻辑，类似于JDK中的InvocationHandler接口
 *
 * @author duwenxu
 * @create 2019-02-28 14:53
 */
class InterceptDemo : MethodInterceptor {
    override fun intercept(o: Any, method: Method, objects: Array<Any>, methodProxy: MethodProxy): Any {
        println("开启事务....")
        val obj = methodProxy.invokeSuper(o, objects)
        println("结束事务....")
        return obj
    }
}
