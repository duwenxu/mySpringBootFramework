package com.example.SpringBoot.KotlinTest.annotationtest.runtime.annotations;

/**
 * sql注解   (自定义运行时注解)
 * 表注解
 * @author duwenxu
 * @create 2019-04-29 10:20
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBtable{
    String name() default "";
}


