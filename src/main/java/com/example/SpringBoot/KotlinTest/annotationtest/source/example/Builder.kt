package com.example.SpringBoot.KotlinTest.annotationtest.source.example


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Builder(val value:String)