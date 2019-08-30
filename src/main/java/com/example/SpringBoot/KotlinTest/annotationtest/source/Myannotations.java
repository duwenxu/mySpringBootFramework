package com.example.springboot.KotlinTest.annotationtest.source;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 编译时注解
 *
 * @author duwenxu
 * @create 2019-04-30 16:40
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
//@Retention(RetentionPolicy.CLASS)
public @interface Myannotations {
    String value() default "";
}
