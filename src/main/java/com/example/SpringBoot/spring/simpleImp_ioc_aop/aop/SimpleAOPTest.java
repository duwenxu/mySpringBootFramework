package com.example.springboot.spring.simpleImp_ioc_aop.aop;

import com.example.springboot.spring.simpleImp_ioc_aop.aop.advice.AfterAdvice;
import com.example.springboot.spring.simpleImp_ioc_aop.aop.aspectImpl.MethodInvocation;
import com.example.springboot.spring.simpleImp_ioc_aop.aop.userdao.IUserDao;
import com.example.springboot.spring.simpleImp_ioc_aop.aop.userdao.UserDaoImpl;
import org.junit.Test;

/**
 * 简单AOP 实现的测试类
 *
 * @author duwenxu
 * @create 2019-09-02 15:15
 */
public class SimpleAOPTest {

    @Test
    public void simpleAopTest(){
        MethodInvocation invocation=()-> System.out.println("current className:"+this.getClass().getSimpleName());
        UserDaoImpl userDao = new UserDaoImpl();

        AfterAdvice afterAdvice = new AfterAdvice(userDao, invocation);

        IUserDao proxyUserdao = (IUserDao) SimpleAOP.getProxy(userDao, afterAdvice);

        proxyUserdao.use();
    }
}
