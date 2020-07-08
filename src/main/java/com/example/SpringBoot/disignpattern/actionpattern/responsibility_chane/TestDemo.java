package com.example.springboot.disignpattern.actionpattern.responsibility_chane;

import com.example.springboot.disignpattern.actionpattern.responsibility_chane.concretehandler.HeadHandler;
import com.example.springboot.disignpattern.actionpattern.responsibility_chane.concretehandler.MiddleHandler;
import com.example.springboot.disignpattern.actionpattern.responsibility_chane.concretehandler.TailHandler;

/**
 *  责任链模式 测试
 * @author duwenxu
 * @create 2020-07-03 16:36
 */
public class TestDemo {

    public static void main(String[] args) {
        HeadHandler head = new HeadHandler("HEAD");
        MiddleHandler middle = new MiddleHandler("MIDDLE");
        TailHandler tail = new TailHandler("TAIL");

        head.setNextHandler(middle);
        middle.setNextHandler(tail);

        head.handleRequest("HEAD");
    }
}
