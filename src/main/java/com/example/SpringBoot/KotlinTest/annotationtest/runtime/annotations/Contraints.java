package com.example.springboot.KotlinTest.annotationtest.runtime.annotations;

/**
 * 约束注解
 *
 * @author duwenxu
 * @create 2019-04-29 10:29
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Contraints{
    boolean isPrimaryKey() default false;
    boolean allowNull() default false;
    boolean isUnique() default false;
}
