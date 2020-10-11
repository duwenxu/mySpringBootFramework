package com.example.springboot.disignpattern.actionpattern.iterator;

import com.example.springboot.disignpattern.actionpattern.iterator.aggregation.ConcreteAggregate;
import com.example.springboot.disignpattern.actionpattern.iterator.iterator.Iterator;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试迭代器模式
 *
 * @author duwenxu
 * @create 2020-09-10 15:45
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.test();
    }

    /**
     * 迭代器调用遍历
     */
    public void test() {
        Object[] objArray = {"One", "Two", "Three", "Four", "Five", "Six"};

        ConcreteAggregate aggregate = new ConcreteAggregate(objArray);
        Iterator iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(String.format("currentItem:%s",iterator.currentItem()));
            iterator.next();
        }
    }

}
