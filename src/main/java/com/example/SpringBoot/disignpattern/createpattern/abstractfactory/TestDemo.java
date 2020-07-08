package com.example.springboot.disignpattern.createpattern.abstractfactory;

import com.example.springboot.disignpattern.createpattern.abstractfactory.factory.AbstractFactory;
import com.example.springboot.disignpattern.createpattern.abstractfactory.factory.HPFactory;
import com.example.springboot.disignpattern.createpattern.abstractfactory.factory.LenoveFactory;

/**
 * @author duwenxu
 * @create 2020-06-30 9:49
 */
public class TestDemo {

    public static void main(String[] args) {
        AbstractFactory hpFactory = new HPFactory();
        LenoveFactory lenoveFactory = new LenoveFactory();

        hpFactory.createKeyBoard().getKeyBoardInfo();
        hpFactory.createMouse().getMouseInfo();

        lenoveFactory.createKeyBoard().getKeyBoardInfo();
        lenoveFactory.createMouse().getMouseInfo();

    }

}
