package com.example.SpringBoot.fastDFS.shequban;

import com.example.SpringBoot.common.Utils.FtpUtil;
import com.example.SpringBoot.fastDFS.FastDFSFile;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * 封装FastDFS上传工具类
 *
 * @author duwenxu
 * @create 2019-04-04 11:21
 */
public class JavaFastDFSClient {
    private static Logger logger = LoggerFactory.getLogger(JavaFastDFSClient.class);

    static {
        try {
            String filepath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filepath);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!", e);
        }
    }

    private static String[] upload(FastDFSFile file) {
        logger.info("filename:" + file.getName() + ",  fileLength:" + file.getContent());
        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            StorageClient storageClient = getTrackerClient();
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), null);
            if (uploadResults[0] != null && uploadResults[1] != null) {
                logger.info("file upload success!" + "group_name=" + uploadResults[0] + ",remote_FileName=" + uploadResults[1]);
            } else {
                logger.error("file" + file.getName() + " upload fail:" + storageClient.getErrorCode());
            }

        } catch (Exception e) {
            logger.error("file" + file.getName() + " upload fail:" + e.getMessage());
        }
        logger.info("file upload use time" + (System.currentTimeMillis() - startTime) + "ms");
        return uploadResults;
    }

    /**
     * @Descriptiona 根据 groupName 和文件名获取文件信息
     * @Params: [groupName, remoteFileName]
     * @Return: * @return : org.csource.fastdfs.FileInfo
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getTrackerClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e.getMessage());
        }
        return null;
    }

    /***
     * @Description: 根据 groupName 和文件名   下载文件
     * @Params: [groupName, remoteFileName]
     * @Return: * @return : java.io.InputStream
     */
    public static InputStream downLoadFile(String groupName, String remoteFileName) throws IOException {
        StorageClient storageClient = getTrackerClient();
        try {
            byte[] download_file = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(download_file);
            return inputStream;
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static StorageClient getTrackerClient() throws IOException {
        return new StorageClient(getTrackerServer(), null);
    }

    private static TrackerServer getTrackerServer() throws IOException {
        return new TrackerClient().getConnection();
    }

    /**
     * query storage servers to upload file
     * @param groupame
     */
    private static StorageServer[] getStoreStorages(String groupame) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupame);
    }

    /**
     * get storage servers to download file
     * @param groupame
     * @param remoteFilename
     */
    private static ServerInfo[] getFetchStorages(String groupame, String remoteFilename) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupame, remoteFilename);
    }


    public static String getTrackerUrl() throws IOException {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    public static void main(String[] args) {
        //上传
        File file = new File("E://picture.jpg");
        byte[] fileBytes=null;
        try {
            fileBytes = FtpUtil.Companion.input2Bytes(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FastDFSFile dfsFile = new FastDFSFile("picture", fileBytes, "jpg");
        System.out.println(JavaFastDFSClient.upload(dfsFile).toString());

        //下载
        try {
            InputStream inputStream = downLoadFile("group1", "M00/00/00/rBUCTVylvhmAPZcSAAAEAFMs1ew730.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
