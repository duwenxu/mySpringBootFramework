package com.example.springboot.disignpattern.actionpattern.iterator.iterator;

/**
 * 迭代器接口
 * 定义遍历元素需要的方法
 *
 * @author duwenxu
 * @create 2020-09-10 15:23
 */
public interface Iterator {

    /**
     * 迭代方法：移动到第一个元素
     */
    void first();

    /**
     * 迭代方法：移动到下一个元素
     */
    void next();

    /**
     * 迭代方法：是否为最后一个元素
     */
    boolean hasNext();

    /**
     * 迭代方法：返还当前元素
     */
    Object currentItem();
}
