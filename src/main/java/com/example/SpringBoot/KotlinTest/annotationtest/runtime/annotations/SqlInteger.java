package com.example.springboot.kotlintest.annotationtest.runtime.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 整数注解
 *
 * @author duwenxu
 * @create 2019-04-29 10:28
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlInteger{
    String name() default "";

    Contraints contraints() default @Contraints; //嵌套注释可将default值设置为该注释默认值
}
