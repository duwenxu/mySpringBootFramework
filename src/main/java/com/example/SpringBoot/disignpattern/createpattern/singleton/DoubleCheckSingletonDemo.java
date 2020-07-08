package com.example.springboot.disignpattern.createpattern.singleton;

import com.example.springboot.disignpattern.createpattern.singleton.impl.Singleton;

/**
 * 单例模式
 * <p>
 * DoubleCheck 双重检查锁
 *
 * @author duwenxu
 * @create 2020-06-29 16:41
 */
public class DoubleCheckSingletonDemo implements Singleton {

    private volatile static DoubleCheckSingletonDemo singleton;

    private DoubleCheckSingletonDemo() {
    }

    public DoubleCheckSingletonDemo getInstance() {
        if (null == singleton) {
            synchronized (DoubleCheckSingletonDemo.class) {
                if (null == singleton) {
                    singleton = new DoubleCheckSingletonDemo();
                }
            }
        }
        return singleton;
    }

}
