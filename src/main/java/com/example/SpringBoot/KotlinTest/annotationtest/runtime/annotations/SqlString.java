package com.example.springboot.KotlinTest.annotationtest.runtime.annotations;

/**
 * String类型字段
 * @author duwenxu
 * @create 2019-04-29 10:45
 */

import com.example.springboot.KotlinTest.annotationtest.runtime.annotations.Contraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlString{
    String name() default "";
    int length() default 0;   //varchar()的长度
    Contraints contraints() default @Contraints;

}
