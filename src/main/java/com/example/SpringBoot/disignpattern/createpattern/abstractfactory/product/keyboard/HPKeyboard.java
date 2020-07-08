package com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard;

/**
 * @author duwenxu
 * @create 2020-06-30 9:26
 */
public class HPKeyboard implements IKeyBoard {

    @Override
    public void getKeyBoardInfo() {
        System.out.println("I am HP keyBoard");
    }
}
