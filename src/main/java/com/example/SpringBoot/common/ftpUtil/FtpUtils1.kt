//package com.example.SpringBoot.common.ftpUtil
//
//import com.example.SpringBoot.common.ftpUtil.result.MethodResult
//import com.example.SpringBoot.common.ftpUtil.result.UpLoadResult
//import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
//import com.waytogalaxy.display.control.file.model.FileInfo
//import com.waytogalaxy.display.control.file.repository.FileInfoRepository
//import com.waytogalaxy.display.exts.ext
//import com.waytogalaxy.display.exts.toError
//import com.waytogalaxy.display.exts.user
//import com.waytogalaxy.display.model.MethodResult
//import com.waytogalaxy.display.model.UpLoadResult
//import org.apache.commons.net.ftp.FTPClient
//import org.apache.commons.net.ftp.FTPClientConfig
//import org.apache.commons.net.ftp.FTPReply
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatus
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.stereotype.Component
//import org.springframework.web.multipart.MultipartFile
//import java.io.InputStream
//import java.security.Principal
//import java.util.*
//
///**
// * Ftp文件服务器接口
// *          单次访问创建单次连接的 上传下载工具类
// *
// * @property ftp FTPClient
// * @property log (org.slf4j.Logger..org.slf4j.Logger?)
// */
//@Component
//object FtpUtils1 {
//
////    lateinit var ftp: FTPClient
////    var loginInfo = Ftp(Cons.FTP_IPARR, Cons.FTP_PORT, Cons.FTP_USERNAME, Cons.FTP_PASSWORD, "", "")
//
//    @Autowired
//    lateinit var fileInfoRepository: FileInfoRepository
//
//    /**
//     * 连接Ftp服务器
//     * @param f Ftp
//     * @return Boolean
//     */
//    fun connectFtp(f: Ftp): Pair<Boolean, FTPClient> {
//        val ftp = FTPClient()
//        ftp.controlEncoding = "UTF-8"
//        try {
//            when (f.port == null) {
//                true -> ftp.connect(f.ipAddr, 21)
//                else -> ftp.connect(f.ipAddr, f.port)
//            }
//            ftp.login(f.username, f.password)
//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE)
//
//            //设置linux环境
//            ftp.configure(FTPClientConfig(FTPClientConfig.SYST_UNIX))
//            //判断是否连接成功
//            if (!FTPReply.isPositiveCompletion(ftp.replyCode)) {
//                toError("FTP服务器拒绝连接!")
//                closeFtp(ftp)
//                return false to ftp
//            }
//
//            //设置访问被动模式
//            ftp.isRemoteVerificationEnabled
//            ftp.enterLocalPassiveMode()
//
//            if (!ftp.changeWorkingDirectory("/${f.filePath}")) {
//                ftp.makeDirectory(f.filePath)
//            }
////            log.info("连接成功！--------------------")
//            return true to ftp
//        } catch (e: Exception) {
//            closeFtp(ftp)
//            toError("$e")
//        }
//        return false to ftp
//    }
//
//    /**
//     * 关闭Ftp连接
//     */
//    fun closeFtp(ftp: FTPClient) {
//        if (ftp.isConnected) {
//            try {
//                ftp.logout()
//                ftp.disconnect()
//            } catch (e: Exception) {
//                toError("关闭连接出错:$e")
//                ftp.disconnect()
//            }
//        }
//    }
//
//    /**
//     * 获取下载文件流
//     * @param f Ftp
//     * @return ResponseEntity<ByteArray>?
//     */
//    fun getFileByteArray(f: Ftp): ResponseEntity<ByteArray> {
//        val ftpCon = connectFtp(f)
//        val ftp = ftpCon.second
//        if (ftpCon.first) {
//            val workDir = f.filePath
//            if (ftp.changeWorkingDirectory("/$workDir")) {
//                ftp.controlEncoding = "UTF-8"
//                ftp.bufferSize = 1024
//                val stream = ftp.retrieveFileStream(f.fileName)
//                val respArr = input2Bytes(stream)
//                closeFtp(ftp)
//                return ResponseEntity(respArr, buildDownHeaders(f.fileName), HttpStatus.CREATED)
//            } else {
//                closeFtp(ftp)
//                toError("下载失败：文件路径$workDir 不存在")
//            }
//        } else {
//            closeFtp(ftp)
//            toError("连接失败")
//        }
//        return ResponseEntity(ByteArray(0), HttpStatus.NO_CONTENT)
//    }
//
//    /**
//     * 添加响应头
//     * @param fileName String
//     * @return HttpHeaders
//     */
//    private fun buildDownHeaders(fileName: String): HttpHeaders {
//        val headers = HttpHeaders()
//        headers.setContentDispositionFormData("attachment", String(fileName.toByteArray(Charsets.UTF_8), Charsets.ISO_8859_1))
//        headers.contentType = MediaType.APPLICATION_OCTET_STREAM
//        return headers
//    }
//
//    /**
//     * 输入流转Bytes
//     * @param inputStream InputStream
//     * @return ByteArray
//     */
//    fun input2Bytes(inputStream: InputStream): ByteArray {
//        val outStream = ByteOutputStream()
//        val buff = ByteArray(100)
//        var rc = 0
//        while ({ rc = inputStream.read(buff, 0, 100);rc }() > 0) {
//            outStream.write(buff, 0, rc)
//        }
//        val bytes = outStream.bytes
//        inputStream.close()
//        outStream.close()
//        return bytes
//    }
//
//    fun upFile(loginInfo: Ftp, file: MultipartFile?, fileDir: String, userId: Int): UpLoadResult {
//        loginInfo.filePath = fileDir
//        val ftpCon = connectFtp(loginInfo)
//        val ftp = ftpCon.second
//        if (ftpCon.first) {
//            var fileName = ""
//            if (!file!!.isEmpty) {
//                val inputStream = file.inputStream
//                fileName = storageLastName(file.originalFilename!!, userId)
//                val absolutePath = storageLastName("$fileDir\\${file.originalFilename}".replace("\\", "/"), userId)
//                ftp.storeFile(fileName, inputStream)
//                inputStream.close()
//                closeFtp(ftp)
//                return UpLoadResult(true, absolutePath, fileName, "上传成功")
//            }
//            closeFtp(ftp)
//            return UpLoadResult(false, "", fileName, "上传文件为空")
//        }
//        closeFtp(ftp)
//        return UpLoadResult(false, "", file?.name, "ftp服务器连接失败")
//    }
//
//    fun ftpUpFiles(loginInfo: Ftp, files: Array<MultipartFile>, fileDir: String, userId: Int): MethodResult<List<FileInfo>?> {
//        loginInfo.filePath = fileDir
//        val list = ArrayList<FileInfo>()
//        if (files.isEmpty()) {
//            return MethodResult(false, null, "上传文件为空")
//        }
//        val ftpCon = connectFtp(loginInfo)
//        val ftp = ftpCon.second
//        if (ftpCon.first) {
//            files.forEach {
//                val filename = it.originalFilename
//                if (filename != null) { //没有名字文件不上传
//                    val upResult = uploadFile(it, userId, fileDir, ftp)
//                    if (!upResult.successed) { //不成功则清理这次失败之前已经保存的文件
//                        list.forEach { fileBean ->
//                            fileBean.id?.let { fileId ->
//                                fileInfoRepository.deleteById(fileId)
//                            }
//                        }
//                        return MethodResult(false, null, "文件上传失败${upResult.fileName}")
//                    }
//                    val storagePath = upResult.absPath
//                    if (storagePath != null) {
//                        val fileBean = FileInfo(upResult.fileName!!, it.ext(), it.size, Date(), storagePath, userId)
//                        fileInfoRepository.save(fileBean)
//                        list.add(fileBean)
//                    }
//                }
//            }
//        } else {
//            closeFtp(ftp)
//            return MethodResult(false, null, "Ftp服务器连接失败")
//        }
//        closeFtp(ftp)
//        return MethodResult(true, list, "上传成功")
//    }
//
//    private fun storageLastName(fileName: String, id: Int) = "${fileName}___$id"
//
//    /**
//     * 删除文件
//     * @param f Ftp
//     * @param fielName String
//     */
//    fun deleFile(loginInfo: Ftp): Boolean {
//        val ftpCon = connectFtp(loginInfo)
//        val ftp = ftpCon.second
//        if (ftpCon.first) {
//            try {
//                val code = ftp.dele(loginInfo.fileName)
//                return code == 250
//            } catch (e: Exception) {
//                toError("删除失败！Exception:$e")
//            } finally {
//                closeFtp(ftp)
//            }
//        } else {
//            closeFtp(ftp)
//            toError("ftp服务器连接失败")
//        }
//        return false
//    }
//
//    private fun uploadFile(file: MultipartFile, id: Int, dirPath: String, ftp: FTPClient): UpLoadResult {
//        var fileName = ""
//        if (!file.isEmpty) {
//            val inputStream = file.inputStream
//            fileName = storageLastName(file.originalFilename!!, id)
//            val absolutePath = storageLastName("$dirPath\\${file.originalFilename}".replace("\\", "/"), id)
//            ftp.storeFile(fileName, inputStream)
//            inputStream.close()
//            closeFtp(ftp)
//            return UpLoadResult(true, absolutePath, fileName, "上传成功")
//        }
//        closeFtp(ftp)
//        return UpLoadResult(false, "", fileName, "文件为空")
//    }
//
//    fun getFtpFiles(dirPath: String, loginInfo: Ftp): List<String> {
//        loginInfo.filePath = dirPath
//        val ftpCon = connectFtp(loginInfo)
//        val ftp = ftpCon.second
//        if (ftpCon.first) {
//            val listFiles = ftp.listFiles()
//            return listFiles.map { it.name }
//        } else {
//            toError("ftp服务器连接失败")
//            closeFtp(ftp)
//        }
//        return listOf()
//    }
//
//
//}
