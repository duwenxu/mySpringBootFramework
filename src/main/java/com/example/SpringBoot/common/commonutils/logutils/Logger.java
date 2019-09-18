package com.example.springboot.common.commonutils.logutils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Log实现类
 *
 * @author duwenxu
 * @create 2019-09-17 9:22
 */
@Component
@Slf4j
public class Logger {

    public Logger() {
        LogUtils.logger=this;
    }

    public void info(String text){
        log.info(text);
    }

    public void warn(String text){
        log.warn(text);
    }

    public void error(String text){
        log.error(text);
    }
}
