package com.example.springboot.disignpattern.createpattern.abstractfactory.factory;

import com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard.HPKeyboard;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard.IKeyBoard;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse.HPMouse;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse.IMouse;

/**
 * @author duwenxu
 * @create 2020-06-30 9:45
 */
public class HPFactory implements AbstractFactory {

    @Override
    public IMouse createMouse() {
        return new HPMouse();
    }

    @Override
    public IKeyBoard createKeyBoard() {
        return new HPKeyboard();
    }
}
