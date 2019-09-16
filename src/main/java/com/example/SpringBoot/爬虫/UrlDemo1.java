package com.example.springboot.爬虫;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 多线程，2层深度爬取网页数据
 *
 * @author duwenxu
 * @create 2019-09-16 14:19
 * <p>
 * 思路：
 * a. 判断当前url是否已爬取过
 * b. 建立连接获取流，进行正则匹配，并将匹配符合规则的url加入到等待处理队列
 * c. 递归调用进行下一层的url抓取处理
 *
 *  主要的需要注意的地方：
 *      url的规则匹配和截取处理
 */
public class UrlDemo1 {
    public static String searchUrl = "http://book.dangdang.com/";
    public static String saveDir = "E://webcontroller/";
    public static ArrayList<String> allWritUrl = new ArrayList();
    public static Set<String> allOverUrl = new HashSet<>();     //已完成爬取的url集合
    public static Map<String, Integer> url2Deep = new HashMap();
    private static int MAX_THREAD = 20;
    private static int maxDeepth = 2;

    public static void main(String[] args) {
        addUrl(searchUrl, 0);
        startTask();
    }

    private static void urlProcesser(String searchUrl, int deep) {
        if (!allOverUrl.contains(searchUrl) || deep < maxDeepth) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "爬取处理" + searchUrl + "开始");

            try {
                URL u = new URL(searchUrl);
                URLConnection connection = u.openConnection();
                InputStream ips = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "utf-8"));

                Pattern pattern = Pattern.compile("<a .*href=.+</a>");
                /**
                 * 当直接使用 dirname/filename 作为文件路径时，应该先创建父文件夹，否则会报出 FileNotFoundException
                 */
                File file = new File(saveDir + System.currentTimeMillis() + ".txt");
                PrintWriter pw = new PrintWriter(file);
                String line;

                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
                    pw.println(line);
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String href = matcher.group(); //返回匹配到的数据
                        /**
                         * substring(i) 截取从i到末尾的元素，包括i
                         */
                        href = href.substring(href.indexOf("href="));
                        if (href.charAt(5) == '\"') {
                            href = href.substring(6);
                        } else {
                            href = href.substring(5);
                        }
                        //截取结束的条件：引号/ 空格 / ">"
                        try {
                            href = href.substring(0, href.indexOf('\"'));
                        } catch (Exception e) {
                            try {
                                href = href.substring(0, href.indexOf(' '));
                            } catch (Exception e1) {
                                href = href.substring(0, href.indexOf('>'));
                            }
                        }
                        //匹配验证url
                        if (href.startsWith("http://") || href.startsWith("https://")) {
                            addUrl(href, deep);
                        }
                    }
                }
                pw.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            allOverUrl.add(searchUrl);
            System.out.println("当前线程" + Thread.currentThread().getName() + "爬取网页" + searchUrl + " 已完成，已爬取数量" + allOverUrl.size() + ",待爬取数量" + allWritUrl.size());

            if (allWritUrl.size() > 0) {

            }
        }
    }

    private static void addUrl(String url, int deepth) {
        allWritUrl.add(url);
        if (!url2Deep.containsKey(url)) {
            url2Deep.put(url, deepth + 1);
        }
    }

    private static synchronized String getUrl() {
        return allWritUrl.remove(0);
    }

    private static void startTask() {
        ExecutorService threadPool = Executors.newFixedThreadPool(200);
        for (int i = 0; i < MAX_THREAD; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (allWritUrl.size() > 0) {
                            String url = getUrl();
                            urlProcesser(url, url2Deep.get(url));
                        } else {
                            System.out.println(Thread.currentThread().getName() + "准备就绪，等待爬取链接");
                        }
                    }
                }
            });
        }
    }


}
