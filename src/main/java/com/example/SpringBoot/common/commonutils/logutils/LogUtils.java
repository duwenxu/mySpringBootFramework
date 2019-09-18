package com.example.springboot.common.commonutils.logutils;

/**
 * Log工具类
 *
 * @author duwenxu
 * @create 2019-09-17 9:28
 */
public class LogUtils {

    public static Logger logger;

    private static void logNotNull(){
        assert(logger!=null);
    }

    public static void info(String text){
        logNotNull();
        logger.info(text);
    }

    public static void warn(String text){
        logNotNull();
        logger.warn(text);
    }

    public static void error(String text){
        logNotNull();
        logger.error(text);
    }

}
