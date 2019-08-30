package com.example.springboot.spring.simpleImp_ioc_aop.ioc;

import org.junit.Test;

import java.util.Objects;

/**
 * SimpleIoc 测试
 *
 * @author duwenxu
 * @create 2019-08-30 17:38
 */
public class SimpleIocTest {

    @Test
    public void getBeanTest() throws Exception {
        String location = Objects.requireNonNull(SimpleIoc.class.getClassLoader().getResource("ioc_test.xml")).getFile();
        SimpleIoc simpleIoc = new SimpleIoc(location);

    }
}
