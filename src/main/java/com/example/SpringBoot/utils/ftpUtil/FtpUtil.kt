package com.waytogalaxy.display.utils.ftpUtil


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import org.apache.commons.net.ftp.FTPReply
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPClientConfig
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

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
        @Throws(IOException::class)
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
                FtpUtil.closeFtp()
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
            if (FtpUtil.connectFtp(f)) {
                val changeDir = ftp.changeWorkingDirectory("/${f.filePath}")
                try {
                    if (changeDir) {
                        ftp.controlEncoding
                        ftp.listFiles()
                                .forEach {
                                    if (f.fileName == it.name) {
                                        downloadFile(f.fileName, localPath)
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
         * @Description:返回包含下载内容字节数组的消息响应体
         * @Date: 2019/3/26
         */
        fun getFileByteArray(f: Ftp): ResponseEntity<ByteArray?>? {
            if (FtpUtil.connectFtp(f)) {
                val changeDir = ftp.changeWorkingDirectory("/${f.filePath}")
                if (changeDir) {
                    ftp.controlEncoding = "UTF-8"
                    ftp.bufferSize = 1024
                    val stream = ftp.retrieveFileStream(f.fileName)
                    return ResponseEntity(input2Bytes(stream), buildDownHeaders(f.fileName), HttpStatus.CREATED)
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

        /**
         * 输入流转ByteArray
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
}

fun main(args: Array<String>) {
    val f = Ftp("172.23.0.21", 21, "ftpadmin", "123456", "/ftp", "CentOS-7-x86_64-DVD-1804.iso")
    val savePath = "E:\\FtpDownload"
    FtpUtil.startDownload(f, savePath)
    FtpUtil.getFileByteArray(f)
}