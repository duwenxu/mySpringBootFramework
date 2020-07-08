package com.example.springboot.disignpattern.createpattern.singleton;

import com.example.springboot.disignpattern.createpattern.singleton.impl.Singleton;

/**
 * 静态内部类
 *
 * @author duwenxu
 * @create 2020-06-29 17:58
 */
public class StaticInnerSingleton implements Singleton {

    private StaticInnerSingleton() {
    }

    public Singleton getInstance() {
        return Inner.singleton;
    }

    private static class Inner{
        private static StaticInnerSingleton singleton = new StaticInnerSingleton();
    }
}
