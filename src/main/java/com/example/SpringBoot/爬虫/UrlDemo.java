package com.example.springboot.爬虫;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 爬虫实现的URL版
 * <p>
 * 通过流读取网页内容并保存
 *
 * @author duwenxu
 * @create 2019-09-16 10:57
 */
public class UrlDemo {

    public static void main(String[] args) {
        String strUrl = "https://www.cnblogs.com/1996swg/p/7355577.html";

        URL url;
        String nativeFile = "D://webcontent.txt";

        {
            try {
                url = new URL(strUrl);
                URLConnection connection = url.openConnection();
                System.out.println(connection.getContentEncoding());

                /**
                 * 字符流的方式
                 */
                InputStream ips = connection.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(ips, "utf-8"));
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nativeFile)));

                String str;
                while ((str = bf.readLine()) != null) {
                    System.out.println(str);
                    bw.write(str);
                    bw.newLine();
                }
                bf.close();
                bw.flush();
                bw.close();

//                /**
//                 * 字节流的方式
//                 *
//                 * 字节流直接读入并写出所有信息，包括空格
//                 * 字符流写出时没有加入空格
//                 */
//                InputStream ips = connection.getInputStream();
//                FileOutputStream fos = new FileOutputStream(nativeFile);
//                byte[] b=new byte[2048];
//                int len=b.length;
//                while ((len=ips.read(b, 0, len))>0){
//                    fos.write(b, 0, len);
//                }
//                fos.flush();
//                ips.close();
//                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
