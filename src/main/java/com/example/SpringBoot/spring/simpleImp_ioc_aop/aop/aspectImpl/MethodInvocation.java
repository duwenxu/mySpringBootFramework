package com.example.springboot.spring.simpleimp_ioc_aop.aop.aspectImpl;

/**
 * 需要加强的切面逻辑
 *
 * @author duwenxu
 * @create 2019-09-02 14:58
 */
public interface MethodInvocation {
    /**
     * 需要调用的方法的具体事项
     */
    void invoke();
}
