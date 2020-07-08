package com.example.springboot.disignpattern.createpattern.simpleFactory;

/**
 * 简单工厂（只适合固定行为的简单场景）
 *
 * 优点： 可以使用户根据参数获得对应的类实例，避免了直接实例化类，降低了耦合性
 * 缺点： 可实例化的类型在编译期间已经被确定，如果增加新类 型，则需要修改工厂，不符合OCP（开闭原则）的原则
 * @author duwenxu
 * @create 2020-06-30 8:55
 */
public class SimpleFactory {
}
