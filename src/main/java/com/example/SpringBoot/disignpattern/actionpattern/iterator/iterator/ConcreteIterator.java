package com.example.springboot.disignpattern.actionpattern.iterator.iterator;

import com.example.springboot.disignpattern.actionpattern.iterator.aggregation.ConcreteAggregate;

/**
 * 具体迭代器类   实现抽象迭代器的方法
 *
 * @author duwenxu
 * @create 2020-09-10 15:25
 */
public class ConcreteIterator implements Iterator {

    /**
     * 被迭代的具体的聚合对象
     */
    private ConcreteAggregate aggregate;

    /**
     * 当前下标
     */
    private int index;

    /**
     * 当前聚合对象的大小
     */
    private int size;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        this.size = aggregate.size();
        this.index = 0;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if (index < size) {
            index++;
        }
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public Object currentItem() {
        return aggregate.getElement(index);
    }
}
