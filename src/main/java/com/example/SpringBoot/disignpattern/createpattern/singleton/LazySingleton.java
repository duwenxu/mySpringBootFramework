package com.example.springboot.disignpattern.createpattern.singleton;

import com.example.springboot.disignpattern.createpattern.singleton.impl.Singleton;

/**
 * 懒汉式（线程不安全，可用双重锁替代）
 *
 * @author duwenxu
 * @create 2020-06-29 17:33
 */
public class LazySingleton implements Singleton {

    private static LazySingleton singleton;

    private LazySingleton() {
    }

    public LazySingleton getInstance() {
        if (null == singleton) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
