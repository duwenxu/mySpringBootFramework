package com.example.springboot.superdog.DogCheck

/**
 * 授权类型
 * @property desc String
 * @property featureCode Int
 * @constructor
 */
enum class FeatureEnum( val desc:String,  val featureCode:Long){

    BY_FREQUENCY("按次",1.toLong()),
    BY_EXPIRED_DATE("按到期时间",2.toLong()),
    BY_USED_TIME("按使用时间天数",3.toLong());


}