package com.example.springboot.spring.simpleImp_ioc_aop.ioc;

import com.example.springboot.spring.simpleImp_ioc_aop.ioc.bean.Car;
import com.example.springboot.spring.simpleImp_ioc_aop.ioc.bean.Wheel;
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
        String location = "D:\\SpringBoot\\src\\main\\java\\com\\example\\springboot\\spring\\simpleImp_ioc_aop\\ioc\\ioc_test.xml";
        SimpleIoc ioc = new SimpleIoc(location);
        Car car =(Car) ioc.getBean("car");
        System.out.println(car);
    }
}
