package com.example.SpringBoot.logConfig;
//tips:insert使光标键切换模式 插入模式--覆盖模式
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 1. 如何在项目中打印日志
 * 新建一个配置类LogConfig，注入一个Bean，并在方法中打印日志
 *
 * 2.如何将日志信息存储到文件
 *
 */

@SpringBootApplication
public class LogConfig {
  private static final Logger LOG = LoggerFactory.getLogger(LogConfig.class);

  @Bean
    public void LogOutput(){
      LOG.info("-------------it's logs!!!!--------------");
  }
}





