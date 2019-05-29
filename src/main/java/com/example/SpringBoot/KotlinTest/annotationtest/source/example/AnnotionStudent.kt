package com.example.SpringBoot.KotlinTest.annotationtest.source.example

@Builder("AnnotionStudent")
class AnnotionStudent(

    @BuilderField("name")
    val name:String,

    @BuilderField("age")
    val age:Int,

    @BuilderField("gender")
    val gender:Int,

    @BuilderField("number")
    val number:Int,

    @BuilderField("score")
    val score:String
)






