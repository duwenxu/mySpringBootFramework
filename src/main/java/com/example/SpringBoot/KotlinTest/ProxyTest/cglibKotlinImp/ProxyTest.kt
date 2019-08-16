package com.example.springboot.KotlinTest.ProxyTest.cglibKotlinImp

import com.example.SpringBoot.KotlinTest.ProxyTest.kotlinImp.PersonDao
import org.junit.Test
import org.springframework.cglib.proxy.Enhancer

/**
 * 生成动态代理类
 *
 * @author duwenxu
 * @create 2019-02-28 14:57
 */
class ProxyTest {

    @Test
    fun test() {
        var person = ProxyFactory() as PersonDao  //获取到代理类并转换为目标类，执行方法
        person.eat()
        person.sleep()
    }


        //生成动态代理类
        fun ProxyFactory(): Any {
            var enhancer = Enhancer()           //创建enhancer增强工具对象
            enhancer.setSuperclass(PersonDao::class.java)     //将被代理类设置为父类
            enhancer.setCallback(InterceptDemo())   //自定义拦截器
            return enhancer.create()
        }

}
