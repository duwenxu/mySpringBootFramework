//package com.example.SpringBoot.common.httpUtils
//
//import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
//import com.waytogalaxy.display.utils.RequestUtil.urlPath
//import org.springframework.web.multipart.MultipartFile
//import java.io.DataOutputStream
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.net.URL
//
///**
// *
// */
//object HttpUtils {
//
//    /**
//     * http下载文件
//     * @param httpUrl String
//     * @param savePath String
//     */
//    fun httpDwnFile(httpUrl: String, savePath: String) {
//        var br = 0
//        var outStream: FileOutputStream? = null
//
//        try {
//            val conn = URL(httpUrl).openConnection()
//            val inputStream = conn.getInputStream()
//            outStream = FileOutputStream(savePath)
//
//            val buffer = ByteArray(1024)
//            while ({ br = inputStream.read(buffer);br }() != -1) {
//                outStream.write(buffer, 0, br)
//            }
//            println("$savePath download finish!")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            outStream?.close()
//        }
//    }
//
//
//    /**
//     * http获取文件bytes
//     * @param httpUrl String
//     */
//    fun getBytes(httpUrl: String): ByteArray {
//        var br = 0
//        val byteOutPutStream = ByteOutputStream()
//        try {
//            val conn = URL(httpUrl).openConnection()
//            val inputStream = conn.getInputStream()
//
//            val buffer = ByteArray(1024)
//            while ({ br = inputStream.read(buffer);br }() != -1) {
//                byteOutPutStream.write(buffer, 0, br)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            byteOutPutStream.close()
//        }
//        return byteOutPutStream.bytes
//    }
//
//    fun dwnFile(httpUrl: String, savePath: String) {
//        val file = File(httpUrl)
//        var outputStream: FileOutputStream? = null
//        try {
//            outputStream = FileOutputStream(savePath)
//            outputStream.write(file.readBytes())
//            println("$savePath download finish!")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            outputStream?.close()
//        }
//    }
//
//    fun uploadFile(savePath: String, filePath: String, creatorId: Int): UpResult {
//        val file = File(filePath)
//        val saveFilePath = "$savePath${filePath.substringAfterLast("/")}"
//        val conn = URL(saveFilePath).openConnection()
//        conn.doOutput = true
//        conn.doInput = true
//        val outStream = conn.getOutputStream()
//        var br = 0
//        try {
//            if (!file.exists()) {
//                return UpResult(false, "文件不存在")
//            }
//            val fis = FileInputStream(file)
//
//            val buffer = ByteArray(1024)
//            while ({ br = fis.read(buffer);br }() != -1) {
//                outStream.write(buffer, 0, br)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        } finally {
//            outStream.flush()
//            outStream.close()
//        }
//        return UpResult(true, "文件上传成功")
//    }
//
//
//}
//
//fun main(args: Array<String>) {
//    val savePath = "http://10.150.0.11/download/gelong/fat/"
//    val filePath = "E:/fileTransTest/centralSchedule.xml"
//    println(HttpUtils.uploadFile(savePath, filePath, 1))
//}
