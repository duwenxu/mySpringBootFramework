package com.example.springboot.disignpattern.createpattern.singleton;


/**
 * 枚举获取单例
 *
 *
 * 枚举实例的创建是线程安全的，并且在任何情况下都是单例。实际上
 *
 * 枚举类隐藏了私有的构造器。
 * 枚举类的域 是相应类型的一个实例对象
 */
public enum EnumSingleton {
    INSTANCE;



    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.INSTANCE;
    }
}

