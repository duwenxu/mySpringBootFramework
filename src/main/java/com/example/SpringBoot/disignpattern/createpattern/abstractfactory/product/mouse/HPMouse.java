package com.example.springboot.disignpattern.createpattern.abstractfactory.product.mouse;

/**
 * @author duwenxu
 * @create 2020-06-30 9:26
 */
public class HPMouse implements IMouse {

    @Override
    public void getMouseInfo() {
        System.out.println("I am HP mouse");
    }
}
