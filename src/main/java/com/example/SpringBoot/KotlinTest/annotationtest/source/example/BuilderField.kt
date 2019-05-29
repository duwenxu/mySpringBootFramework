package com.example.SpringBoot.KotlinTest.annotationtest.source.example

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class BuilderField(val value:String)