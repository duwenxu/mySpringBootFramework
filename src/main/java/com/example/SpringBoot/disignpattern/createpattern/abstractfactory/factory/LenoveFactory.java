package com.example.springboot.disignpattern.createpattern.abstractfactory.factory;

import com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard.IKeyBoard;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard.LenovoKeyboard;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse.IMouse;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse.LenoveMouse;

/**
 * @author duwenxu
 * @create 2020-06-30 9:48
 */
public class LenoveFactory implements AbstractFactory {

    @Override
    public IMouse createMouse() {
        return new LenoveMouse();
    }

    @Override
    public IKeyBoard createKeyBoard() {
        return new LenovoKeyboard();
    }
}
