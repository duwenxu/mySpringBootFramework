package com.example.SpringBoot.webSocket

import org.springframework.stereotype.Controller
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Controller
//WebMvcConfigurerAdapter 已过时
/**
 *  1 直接实现WebMvcConfigurer （官方推荐）
    2 直接继承WebMvcConfigurationSupport：2019-03-08 18:08:26

    一.WebMvcConfigurerAdapter是什么
    Spring内部的一种配置方式
    采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制
 */
class WebViewController : WebMvcConfigurationSupport() {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        /**
         * 视图跳转控制器
          void addViewControllers(ViewControllerRegistry registry);
         */
        registry.addViewController("/webSocketIndex").setViewName("/webSocketIndex")
    }
}