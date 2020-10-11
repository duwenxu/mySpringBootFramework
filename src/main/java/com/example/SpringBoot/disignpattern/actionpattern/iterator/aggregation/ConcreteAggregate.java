package com.example.springboot.disignpattern.actionpattern.iterator.aggregation;

import com.example.springboot.disignpattern.actionpattern.iterator.iterator.ConcreteIterator;
import com.example.springboot.disignpattern.actionpattern.iterator.iterator.Iterator;

/**
 * 具体聚集类
 * 实现抽象聚集类创建迭代器并提供额外方法
 *
 * @author duwenxu
 * @create 2020-09-10 15:20
 */
public class ConcreteAggregate implements Aggregate {

    private Object[] objArray;

    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 获取长度
     * @return
     */
    public int size(){
        return objArray.length;
    }

    /**
     * 对外提供元素取值
     * @param index
     * @return
     */
    public Object getElement(int index){
        if (index< objArray.length){
            return objArray[index];
        }else {
            return null;
        }

    }
}
