package com.example.springboot.spring.simpleImp_ioc_aop.aop.advice;

import com.example.springboot.spring.simpleImp_ioc_aop.aop.aspectImpl.MethodInvocation;

import java.lang.reflect.Method;

/**
 * 后置通知的实现类    对应动态代理 InvocationHandler 中的增强逻辑
 *
 * @author duwenxu
 * @create 2019-09-02 15:03
 */
public class AfterAdvice implements Advice{

    private Object bean;
    private MethodInvocation methodInvocation;

    public AfterAdvice(Object bean, MethodInvocation methodInvocation){
            this.bean=bean;
            this.methodInvocation=methodInvocation;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public MethodInvocation getMethodInvocation() {
        return methodInvocation;
    }

    public void setMethodInvocation(MethodInvocation methodInvocation) {
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object proxyObj = method.invoke(bean, args);
        //后置通知
        methodInvocation.invoke();
        return proxyObj;
    }
}
