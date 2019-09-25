package com.example.springboot.kotlintest.annotationtest.source.example

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class BuilderField(val value:String)