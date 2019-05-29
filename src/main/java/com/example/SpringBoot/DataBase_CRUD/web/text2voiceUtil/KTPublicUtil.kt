package com.example.SpringBoot.DataBase_CRUD.web.text2voiceUtil

import com.alibaba.fastjson.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

//object关键字修饰类，类中均为静态方法
object KTPublicUtil {
    //用companion object{}包裹，使其为静态方法

     open fun getAuth(): String? {
        var clientId = "Y7fswb83HB2s5LGyDaVquBo5"
        var clientSecret = "MsqY3jAsTM9r5duUpvWExk88bqG303eI"
        return getAuth(clientId, clientSecret)
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取
     *
     * @param ak
     * @param sk
     * @return
     */
    fun getAuth(ak: String, sk: String): String? {
        var authHoost = "https://openapi.baidu.com/oauth/2.0/token?"
        var getAccessTokenUrl =authHoost + "grant_type=client_credentials&client_id=$ak&client_secret=$sk"

        try {
            var realURL: URL = URL(getAccessTokenUrl)
            //kotlin中as  强制类型转化
            var connection: HttpURLConnection = realURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            //获取所有的响应头字段
            val map: MutableMap<String, List<String>> = connection.headerFields   //kotlin中的var变量自带get方法
            for ((key, value) in map) {
                println("$key--->$value")
            }

            //定义 BufferedReader输入流来读取URL的响应
            var buffer: BufferedReader = BufferedReader(InputStreamReader(connection.inputStream))
            var result = ""
            var line: String? = ""
            while ({ line = buffer.readLine(); line }() != null) {   //kotlin不支持在条件里面包含赋值语句
                result += line
            }

            /**
             * 返回结果
             */
            println("result:$result")
            var jsonObject = JSONObject.parseObject(result)
            var access_token = jsonObject.getString("access_token")
            return access_token
        } catch (e: Exception) {
            println("获取token失败！")
            e.printStackTrace(System.err)
        }
        return null
    }

    /**
     * 语音合成HTTP方法
     * @param requestUrl
     * @param params
     * @return
     * @throws Exception
     */
    open fun postVoice(requestUrl: String, params: String): String {
        println("直接访问链接：$requestUrl?$params")
        // var path=System.getProperty("user.home")+"/text2audio/"
        var path = "F:\\"
        try {
            if (!File(path).isDirectory) {
                File(path).mkdir()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var filePath = path + "VOICE" + Date().time / 1000 + ".mp3"
        var generalUrl = requestUrl
        println(generalUrl)
        var url = URL(generalUrl)

        // 打开和URL之间的连接
        val connection = url.openConnection() as HttpURLConnection
        println("打开链接，开始发送请求" + Date().time / 1000)
        connection.requestMethod = "POST"
        //设置通用的请求属性
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        connection.setRequestProperty("Connection", "Keep-Alive")
        connection.useCaches = false
        connection.doInput = true
        connection.doOutput = true

        //得到请求的输出流对象
        val out = DataOutputStream(connection.outputStream)
        out.writeBytes(params)
        out.flush()
        out.close()

        // 建立实际的连接
        connection.connect()
        val headeras = connection.headerFields
        for ((key, value) in headeras) {
            println("$key--->$value")
        }
        //定义 BufferedReader输入流来读取URL的响应
        val inputStream = connection.inputStream
        val outputStream = FileOutputStream(filePath)
        val buffer = ByteArray(1024)
        var len = -1
        /**
         * 不同于java的遍历方式
         */
        while ({ len = inputStream.read(buffer);len }() != -1) {
            outputStream.write(buffer, 0, len)
        }
        outputStream.close()
        println("请求结束" + Date().time / 1000)
        println("音频MP3文件的保存目录：$filePath")
        return filePath
    }

    /**
     *
    * @Description:
    * @Param:
    * @return:  
    * @Author: duwenxu 
    * @Date: 2019/2/21 
    */ 
    open fun getRandomStringByLength(lengeth:Int):String?{
        var base="abcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        val sb = StringBuffer()
        for (i in 0..lengeth){
            val number = random.nextInt(base.length)
            sb.append(base[number])   //Kotlin字符串自带遍历功能，可以以数组的形式获取元素
        }
        return sb.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(getAuth())
    }
}