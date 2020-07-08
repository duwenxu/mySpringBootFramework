package com.example.springboot.disignpattern.createpattern.abstractfactory.factory;

import com.example.springboot.disignpattern.createpattern.abstractfactory.product.keyboard.IKeyBoard;
import com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse.IMouse;

/**
 * 抽象工厂
 *
 * @author duwenxu
 * @create 2020-06-30 9:23
 */
public interface AbstractFactory {

    public IMouse createMouse();

    public IKeyBoard createKeyBoard();
}
