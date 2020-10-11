package com.example.springboot.disignpattern.actionpattern.iterator.aggregation;

import com.example.springboot.disignpattern.actionpattern.iterator.iterator.Iterator;

/**
 * 抽象聚集类
 *
 * @author duwenxu
 * @create 2020-09-10 15:18
 */
public interface Aggregate {

    /**
     * 创建迭代子
     * @return
     */
    public Iterator createIterator();
}
