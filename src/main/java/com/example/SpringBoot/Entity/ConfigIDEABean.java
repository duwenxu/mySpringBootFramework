package com.example.SpringBoot.Entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
//从application.properties中引入属性绑定对象
@ConfigurationProperties(prefix ="com.test")
public class ConfigIDEABean
{
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
