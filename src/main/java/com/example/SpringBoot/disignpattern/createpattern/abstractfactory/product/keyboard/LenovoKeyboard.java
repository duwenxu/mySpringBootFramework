package com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard;

/**
 * @author duwenxu
 * @create 2020-06-30 9:28
 */
public class LenovoKeyboard implements IKeyBoard {

    @Override
    public void getKeyBoardInfo() {

        System.out.println("I am lenove keyBoard");
    }
}
