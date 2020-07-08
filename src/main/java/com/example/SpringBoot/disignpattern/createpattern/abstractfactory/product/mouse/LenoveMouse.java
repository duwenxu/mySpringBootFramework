package com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse;

/**
 * @author duwenxu
 * @create 2020-06-30 9:28
 */
public class LenoveMouse implements IMouse {

    @Override
    public void getMouseInfo() {

        System.out.println("I am lenove mouse");
    }
}
