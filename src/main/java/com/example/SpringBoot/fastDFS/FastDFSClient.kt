package com.example.SpringBoot.fastDFS

import com.example.SpringBoot.common.ftpUtil.FtpUtil
import org.csource.common.MyException
import org.csource.fastdfs.*
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource

import java.io.*

/**
 * 封装FastDFS上传工具类
 *
 * @author duwenxu
 * @create 2019-04-04 11:21
 */
object FastDFSClient {
    private val logger = LoggerFactory.getLogger(FastDFSClient::class.java)

    val trackerClient: StorageClient
        @Throws(IOException::class)
        get() = StorageClient(trackerServer, null)

    private val trackerServer: TrackerServer
        @Throws(IOException::class)
        get() = TrackerClient().connection


    val trackerUrl: String
        @Throws(IOException::class)
        get() = "http://" + trackerServer.inetSocketAddress.hostString + ":" + ClientGlobal.getG_tracker_http_port() + "/"

    init {
        try {
            val filepath = ClassPathResource("fdfs_client.conf").file.absolutePath
            ClientGlobal.init(filepath)
        } catch (e: Exception) {
            logger.error("FastDFS Client Init Fail!", e)
        }

    }

    private fun upload(file: FastDFSFile): Array<String>? {
        logger.info("filename:" + file.name + ",  fileLength:" + file.content)
        val startTime = System.currentTimeMillis()
        var uploadResults: Array<String>? = null
        try {
            val storageClient = trackerClient
            uploadResults = storageClient.upload_file(file.content, file.ext, null)
            if (uploadResults!![0] != null && uploadResults[1] != null) {
                logger.info("file upload success!" + "group_name=" + uploadResults[0] + ",remote_FileName=" + uploadResults[1])
            } else {
                logger.error("file" + file.name + " upload fail:" + storageClient.errorCode)
            }

        } catch (e: Exception) {
            logger.error("file" + file.name + " upload fail:" + e.message)
        }

        logger.info("file upload use time" + (System.currentTimeMillis() - startTime) + "ms")
        return uploadResults
    }

    /**
     * @Descriptiona 根据 groupName 和文件名获取文件信息
     * @Params: [groupName, remoteFileName]
     * @Return: * @return : org.csource.fastdfs.FileInfo
     */
    fun getFile(groupName: String, remoteFileName: String): FileInfo? {
        try {
            val storageClient = trackerClient
            return storageClient.get_file_info(groupName, remoteFileName)
        } catch (e: Exception) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e.message)
        }

        return null
    }

    /***
     * @Description: 根据 groupName 和文件名   下载文件
     * @Params: [groupName, remoteFileName]
     * @Return: * @return : java.io.InputStream
     */
    @Throws(IOException::class)
    fun downLoadFile(groupName: String, remoteFileName: String): InputStream? {
        val storageClient = trackerClient
        try {
            val download_file = storageClient.download_file(groupName, remoteFileName)
            return ByteArrayInputStream(download_file)
        } catch (e: MyException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * query storage servers to upload file
     * @param groupame
     */
    @Throws(IOException::class)
    private fun getStoreStorages(groupame: String): Array<StorageServer> {
        val trackerClient = TrackerClient()
        val trackerServer = trackerClient.connection
        return trackerClient.getStoreStorages(trackerServer, groupame)
    }

    /**
     * get storage servers to download file
     * @param groupame
     * @param remoteFilename
     */
    @Throws(IOException::class)
    private fun getFetchStorages(groupame: String, remoteFilename: String): Array<ServerInfo> {
        val trackerClient = TrackerClient()
        val trackerServer = trackerClient.connection
        return trackerClient.getFetchStorages(trackerServer, groupame, remoteFilename)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        //上传
        val file = File("E://picture.jpg")
        var fileBytes: ByteArray? = null
        try {
            fileBytes = FtpUtil.input2Bytes(FileInputStream(file))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val dfsFile = FastDFSFile("picture", fileBytes!!, "jpg")
        println(FastDFSClient.upload(dfsFile)!!.toString())

        //下载
        try {
            val inputStream = downLoadFile("group1", "M00/00/00/rBUCTVylvhmAPZcSAAAEAFMs1ew730.xml")
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


}
