package com.example.SpringBoot.common.ftpUtil

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPReply
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPClientConfig
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.io.*


class FtpUtil {
    companion object {
        lateinit var ftp: FTPClient
        val log = LoggerFactory.getLogger(this::class.java)
        //FTP info: 172.23.0.21 -22    ftpadmin  123456

        /**
         * @Description:  获取ftp连接
         * @Author: duwenxu
         * @Date: 2019/3/25
         */
        fun connectFtp(f: Ftp): Boolean {
            ftp = FTPClient()
            ftp.controlEncoding="UTF-8"
            try {
                when (f.port == null) {
                    true -> ftp.connect(f.ipAddr, 21)
                    else -> ftp.connect(f.ipAddr, f.port)
                }
                ftp.login(f.username, f.password)
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE)

                //设置linux环境
                ftp.configure(FTPClientConfig(FTPClientConfig.SYST_UNIX))
                //判断是否连接成功
                if (!FTPReply.isPositiveCompletion(ftp.replyCode)) {
                    log.error("FTP服务器拒绝连接！")
                    ftp.disconnect()
                    return false
                }

                //设置访问被动模式
                ftp.isRemoteVerificationEnabled
                ftp.enterLocalPassiveMode()

                ftp.changeWorkingDirectory("/${f.filePath}")
                log.info("连接成功！--------------------")
                return true
            } catch (e: Exception) {
                log.error("$e")
            }
            return false
        }

        /**
         * @Description:  关闭连接
         * @Author: duwenxu
         * @Date: 2019/3/25
         */
        fun closeFtp() {
            if (ftp != null && ftp.isConnected) {
                try {
                    ftp.logout()
                    ftp.disconnect()
                } catch (e: Exception) {
                    log.error("关闭连接出错  $e")
                }
            }
        }

        /**
         * @Description:
         * @Param:
         * @return:
         * @Author: duwenxu
         * @Date: 2019/3/25
         */
        fun startDownload(f: Ftp, localPath: String): Boolean {
            if (connectFtp(f)) {
                val changeDir = ftp.changeWorkingDirectory("/${f.filePath}")
                try {
                    if (changeDir) {
                        ftp.controlEncoding
                        ftp.listFiles()
                                .forEach {
                                    if (f.fileName == it.name) {
                                        downloadFile(f.fileName, localPath)
                                        log.info("下载成功-----------")
                                        return true
                                    }
                                }
                    }
                } catch (e: Exception) {
                    log.error("$e")
                }
            } else {
                log.error("连接失败")
            }
            log.error("下载失败")
            return false
        }

        /**
         * @Description:  从FTP下载文件
         * @Author: duwenxu
         * @Date: 2019/3/25
         */
        fun downloadFile(fileName: String, localPath: String?) {
            val out: FileOutputStream
            try {
                val fileDir = File(localPath)    //下载到本地某个文件夹下
                if (!fileDir.exists() || !fileDir.isDirectory) {
                    fileDir.mkdirs()
                }
                out = FileOutputStream("$localPath\\$fileName")
                ftp.retrieveFile(fileName, out)
                out.flush()
                out.close()
                log.info("下载成功-----------")
            } catch (e: Exception) {
                log.error("$e")
            } finally {
                closeFtp()
            }
        }


        /**
         * @Description:返回包含下载内容字节数组的消息响应体
         * @Date: 2019/3/26
         */
        fun getFileByteArray(f: Ftp): ResponseEntity<ByteArray>? {
            if (connectFtp(f)) {
                val changeDir = ftp.changeWorkingDirectory("/${f.filePath}")
                if (changeDir) {
                    ftp.controlEncoding = "UTF-8"
                    ftp.bufferSize = 1024
                    val stream = ftp.retrieveFileStream(f.fileName)
                    val response=ResponseEntity(input2Bytes(stream), buildDownHeaders(f.fileName), HttpStatus.CREATED)
                    println("数据内容：$response")
                    return response
                }
            } else {
                log.error("连接失败")
            }
            return null
        }

        /**
         * @Description:添加下载响应头信息
         */
        private fun buildDownHeaders(fileName: String): HttpHeaders {
            val headers = HttpHeaders()
            headers.setContentDispositionFormData("attachment", String(fileName.toByteArray(Charsets.UTF_8), Charsets.ISO_8859_1))
            headers.contentType = MediaType.APPLICATION_OCTET_STREAM
            return headers
        }

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

        /**
         * 上传文件
         * @param filePath String
         */
        fun uploadFile(file:File){
//            val file=File(filePath)
            if (file.isDirectory){
                ftp.makeDirectory(file.name)   //创建对应文件夹
                ftp.changeWorkingDirectory(file.name)
                val fileList = file.list()
                fileList.forEach {
                    val file1=File(file.path+"/"+it)
                    if (file1.isDirectory){
                        uploadFile(file1)
                        ftp.changeToParentDirectory()
                    }else{
                        val inputStream=FileInputStream(file1)
                        ftp.storeFile(file1.name,inputStream)
                        inputStream.close()
                        println("${file1.name}--->文件上传成功")
                    }
                }
            }else{
                val file1=File(file.path)
                val inputStream=FileInputStream(file1)
                ftp.storeFile(file1.name,inputStream)
                inputStream.close()
                println("${file1.name}--->文件上传成功")
            }
        }

        /**
         * 加入连接服务器后的上传
         * @param f Ftp
         * @param file File
         */
        fun startUpload(f: Ftp,file:File){
            if (connectFtp(f)){
                try {
                    if (file.isDirectory){
                        ftp.makeDirectory(file.name)   //创建对应文件夹
                        ftp.changeWorkingDirectory(file.name)
                        val fileList = file.list()
                        fileList.forEach {
                            val file1=File(file.path+"/"+it)
                            if (file1.isDirectory){
                                uploadFile(file1)
                                ftp.changeToParentDirectory()
                            }else{
                                val inputStream=FileInputStream(file1)
                                ftp.storeFile(file1.name,inputStream)
                                inputStream.close()
                                println("${file1.name}--->文件上传成功")
                            }
                        }
                    }else{
                        val file1=File(file.path)
                        val inputStream=FileInputStream(file1)
                        ftp.storeFile(file1.name,inputStream)
                        inputStream.close()
                        println("${file1.name}--->文件上传成功")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    closeFtp()
                }
            }else{
                log.error("ftp服务器连接失败！")
            }
        }

        /**
         * 删除文件
         * @param f Ftp
         * @param fielName String
         */
        fun deleFile(f:Ftp){
            if (connectFtp(f)){
                try {
                    val code = ftp.dele(f.fileName)
                    println(code)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    closeFtp()
                }
            }
        }


    }
}

fun main(args: Array<String>) {
    //文件下载
    val f = Ftp("172.23.0.21", 21, "ftpadmin", "123456", "/display/fat", "轨道抬高描述文件201901281056.xml")
    val savePath = "E:\\FtpDownload"
    FtpUtil.startDownload(f, savePath)
    FtpUtil.getFileByteArray(f)

    //文件上传
    val upF = Ftp("172.23.0.21", 21, "ftpadmin", "123456", "/display/fat","")
    val file=File("C:\\datainject\\savedesc\\1\\XML\\轨道抬高描述文件201901281056.xml")
    FtpUtil.startUpload(upF,file)

    //文件删除
    val delF = Ftp("172.23.0.21", 21, "ftpadmin", "123456", "/display/fat/","轨道抬高描述文件201901281056.xml")
    FtpUtil.deleFile(delF)

}


