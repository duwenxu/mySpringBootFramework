package com.example.SpringBoot.web.text2voiceUtil;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PublicUtil {
    public static String getAuth() {
        //分别为百度云应用的API Key和secret Key
        String clientId = "Y7fswb83HB2s5LGyDaVquBo5";
        String clientSecret = "MsqY3jAsTM9r5duUpvWExk88bqG303eI ";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取
     *
     * @param ak
     * @param sk
     * @return
     */
    private static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://openapi.baidu.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /**
     * 语音合成HTTP方法
     * @param requestUrl
     * @param params
     * @return
     * @throws Exception
     */
    public static String postVoice(String requestUrl,String params) throws Exception {
        System.out.println("直接访问链接："+requestUrl+"?"+params);
        //String path = System.getProperty("user.home")+"/text2audio/";
        String path="F:\\";
        try {
            if (!(new File(path).isDirectory())) {
                new File(path).mkdir();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        String filePath = path+"VOICE"+new Date().getTime()/1000+".mp3";
        String generalUrl = requestUrl;
        URL url = new URL(generalUrl);
        System.out.println(generalUrl);
        // 打开和URL之间的连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        System.out.println("打开链接，开始发送请求"+new Date().getTime()/1000);
        connection.setRequestMethod("POST");
        // 设置通用的请求属性
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // 得到请求的输出流对象
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(params);
        out.flush();
        out.close();

        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> headers = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            System.out.println(key + "--->" + headers.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应
        InputStream inputStream = connection.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(filePath);
        byte[] buffer = new byte[1024];
        int len ;
        while ((len=inputStream.read(buffer))!=-1) {
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        System.out.println("请求结束"+new Date().getTime()/1000);
        System.out.println("MP3文件保存目录:" + filePath);
        return filePath;
    }

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <length; i++) {
            //public int nextInt(int n)  该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
