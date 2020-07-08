package com.example.springboot.disignpattern.createpattern.singleton;

import com.example.springboot.disignpattern.createpattern.singleton.impl.Singleton;

/**
 * 饿汉式
 *
 * @author duwenxu
 * @create 2020-06-29 17:53
 */
public class HungrySingleton implements Singleton {

    //提前初始化(类创建时初始化) 线程安全
    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public Singleton getInstance() {
        return singleton;
    }
}
