package com.example.springboot.redis.config


/**
 * 数据解析用的数据包含类 包括类名和数据
 * @property className String
 * @property jsonData String
 * @constructor
 */
class ObjContainer(val className: String, val jsonData: String)