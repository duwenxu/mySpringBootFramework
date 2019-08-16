package com.example.springboot.KotlinTest.commontest

import com.alibaba.fastjson.JSONObject
import com.example.springboot.common.commonutils.toFastJsonObj
import com.example.springboot.common.commonutils.toFastObj
import com.example.springboot.common.commonutils.toJson
import com.example.springboot.common.commonutils.toJsonObj


fun main(args: Array<String>) {
    val jsonStr="{\n" +
            "    \"remote\":{\n" +
            "        \"bz_\":0,\n" +
            "        \"bs_\":0,\n" +
            "        \"length_\":0,\n" +
            "        \"time_\":null,\n" +
            "        \"zxl_\":{\n" +
            "            \"bytes\":[\n" +
            "\n" +
            "            ],\n" +
            "            \"hash\":0\n" +
            "        },\n" +
            "        \"code_\":\"\",\n" +
            "        \"name_\":\"\",\n" +
            "        \"device_\":\"\",\n" +
            "        \"thick_\":\"\",\n" +
            "        \"executetime_\":null,\n" +
            "        \"taskFlightId_\":1011,\n" +
            "        \"judgeResult_\":1,\n" +
            "        \"judgeResultMsg_\":\"遥控比判超时:10.0s\",\n" +
            "        \"memoizedIsInitialized\":1,\n" +
            "        \"unknownFields\":{\n" +
            "            \"fields\":{\n" +
            "\n" +
            "            }\n" +
            "        },\n" +
            "        \"memoizedSize\":-1,\n" +
            "        \"memoizedHashCode\":0\n" +
            "    },\n" +
            "    \"head\":{\n" +
            "        \"source_\":2685472768,\n" +
            "        \"destination_\":1996886537,\n" +
            "        \"mission_\":0,\n" +
            "        \"spacecraft_\":2684526593,\n" +
            "        \"type_\":2162946,\n" +
            "        \"time_\":{\n" +
            "            \"seconds_\":1554691221,\n" +
            "            \"nanos_\":113000000,\n" +
            "            \"memoizedIsInitialized\":-1,\n" +
            "            \"unknownFields\":{\n" +
            "                \"fields\":{\n" +
            "\n" +
            "                }\n" +
            "            },\n" +
            "            \"memoizedSize\":-1,\n" +
            "            \"memoizedHashCode\":0\n" +
            "        },\n" +
            "        \"flag_\":0,\n" +
            "        \"dataId_\":\"\",\n" +
            "        \"memoizedIsInitialized\":-1,\n" +
            "        \"unknownFields\":{\n" +
            "            \"fields\":{\n" +
            "\n" +
            "            }\n" +
            "        },\n" +
            "        \"memoizedSize\":-1,\n" +
            "        \"memoizedHashCode\":0\n" +
            "    }\n" +
            "}"


    val jsonObj=jsonStr.toFastObj(JSONObject::class.java)
//    println(jsonObj.toString())

    val any=jsonObj["remote"]
    println(any?.toJsonObj().toString())
    println(any?.toFastJsonObj().toString())
    println(any?.toString())

    println(any?.toJson())
    println(any?.toFastJsonObj())


}