package com.example.springboot.common.hdfs

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.springframework.stereotype.Component
import java.io.*
import java.net.URI


/**
 * HDFS工具类
 *
 * @author duwenxu
 * @create 2019-05-06 10:17
 */
@Component
class HdfsUtil {

    /**
     * 得到文件String内容
     * @param file HdfsFile
     * @return StringBuffer
     */
    fun readFile(file: HdfsFile): StringBuffer {
        var sb = StringBuffer()
        var fs : FileSystem? = null
        var bufferReader: BufferedReader? = null
        try {
            val dsf = "hdfs://${file.hdfsIP}${file.hdfsFileName}"
            println("hdfs文件路径：$dsf")
            val conf = Configuration()

            fs = FileSystem.get(URI.create(dsf), conf)
            bufferReader = BufferedReader(InputStreamReader(fs.open(Path(dsf))))
            val readlines = bufferReader.readLines().filter { it.trim().isNotBlank() }
            readlines.forEach { sb.append("$it\n") }
//            var str = bufferReader.readLine()
//            while (str != null){
//                sb.append("$str\n")
//                str = bufferReader.readLine()
//            }
            bufferReader.close()
            fs.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }finally {
            try {
                bufferReader?.close()
                fs?.close()
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
        return sb
    }

    fun getInputStream(file: HdfsFile):ByteArrayInputStream?{
        val filePath = "hdfs://${file.hdfsIP}${file.hdfsFileName}"
        val conf = Configuration()
        try {
            val fs = FileSystem.get(URI.create(filePath), conf)
            val flag = fs.isFile(Path(filePath))
            if (flag){
                val fsStream = fs.open(Path(filePath))
                val inputStream = fsStream.wrappedStream
                return ByteArrayInputStream(input2Bytes(inputStream))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * InputStream转byteArray
     * @param inputStream InputStream
     * @return ByteArray
     */
    fun input2Bytes(inputStream: InputStream): ByteArray {
        val outStream = ByteOutputStream()
        val buff = ByteArray(100)
        var rc = 0
        while ({ rc = inputStream.read(buff, 0, 100);rc }() > 0) {
            outStream.write(buff, 0, rc)
        }
        val bytes = outStream.bytes
        outStream.close()
        return bytes
    }
}

fun main(args: Array<String>) {
    val file1 = HdfsFile("172.23.5.1:9000", "/temp/predictData/TC0302/RLING.TXT")
//    val file2 = HdfsFile("172.23.5.1:9000", "/temp/predictData/TC0101/ELPMOO.XML")
    val fileList= arrayListOf(file1)

    fileList.forEach {
        print(HdfsUtil().readFile(it))
    }
}

