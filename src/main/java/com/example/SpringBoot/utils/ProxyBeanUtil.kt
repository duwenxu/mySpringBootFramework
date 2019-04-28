package com.waytogalaxy.display.utils

import org.springframework.aop.framework.AdvisedSupport
import org.springframework.aop.framework.AopProxy
import org.springframework.aop.support.AopUtils

object ProxyBeanUtil {


    fun getTarget(proxy: Any): Any? {

        if (!AopUtils.isAopProxy(proxy)) {
            return proxy   //不是代理对象
        }
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy)
        } else {
            return getCglibProxyTargetObject(proxy)
        }
    }

    /**
     * 获取jdk动态代理对象
     * @param proxy 代理对象
     * @return 代理对象的目标对象
     */
    private fun getJdkDynamicProxyTargetObject(proxy: Any): Any? {
        val h = proxy.javaClass.superclass.getDeclaredField("h")
        h.isAccessible = true
        val aopProxy = h.get(proxy) as AopProxy
        val advised = aopProxy.javaClass.getDeclaredField("advised")
        advised.isAccessible = true
        return (advised.get(aopProxy) as AdvisedSupport).targetSource.target;
    }

    /**
     * 获取Cglib动态代理对象
     * @param proxy 代理对象
     * @return 代理对象的目标对象
     */
    private fun getCglibProxyTargetObject(proxy: Any): Any? {
        val h = proxy.javaClass.getDeclaredField("CGLIB\$CALLBACK_0")
        h.isAccessible = true
        val dynamicAdvisedInterceptor = h.get(proxy)
        val advised = dynamicAdvisedInterceptor.javaClass.getDeclaredField("advised")
        advised.isAccessible = true
        return (advised.get(dynamicAdvisedInterceptor) as AdvisedSupport).targetSource.target
    }


}