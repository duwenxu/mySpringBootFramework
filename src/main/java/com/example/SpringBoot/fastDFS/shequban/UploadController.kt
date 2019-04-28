package com.example.SpringBoot.fastDFS.shequban

import com.luhuiguo.fastdfs.service.FastFileStorageClient
import org.apache.commons.io.FilenameUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.ColumnMapRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.net.URLEncoder
import javax.servlet.http.HttpServletResponse

@RestController
open class UploadController {
    @Autowired
    lateinit var storageClient: FastFileStorageClient
    @Autowired
    lateinit var JdbcTemplate: JdbcTemplate

    @PostMapping("upload")
    open fun upload(file: MultipartFile): String {
        val extension = FilenameUtils.getExtension(file.originalFilename)
        val storePath = storageClient.uploadFile(file.inputStream.readBytes(), extension)
        /**
         * storePath.getFullPath=storePath.group+storePath.path  (全路径=group+路径)
         */
        val sql = "INSERT INTO `FAST_DFS` (`FILE_NAME`, `GROUP_NAME`, `STORE_PATH`, `CREATE_TIME`) VALUES (?,?,?,?)"
        JdbcTemplate.update(sql, file.originalFilename, storePath.group, storePath.path)
        return storePath.fullPath
    }

    @GetMapping("download")
    open fun download(groupName: String, storePath: String, fileId: Int, fileName: String, response: HttpServletResponse) {
        val queryList = JdbcTemplate.query("SELECT * FROM `FAST_DFS` WHERE FILE_ID=$fileId", ColumnMapRowMapper())
        println(queryList.toString())
        val fileMap = queryList[0]
        val fileName = URLEncoder.encode(fileMap[fileName].toString(), "UTF-8")
        val groupName = fileMap[groupName].toString()
        val storePath = fileMap[storePath].toString()
        //设置浏览器下载
        response.setHeader("Content-Disposition", "attachment; filename=$fileName")
        val bytes = storageClient.downloadFile(groupName, storePath)
    }
}