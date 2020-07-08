package com.example.springboot.disignpattern.createpattern.buildpattern.classical;

/**
 *  指导者 类
 * @author duwenxu
 * @create 2020-06-30 14:37
 */
public class ComputeDirector {

    public Compute construct(Builder builder){
        builder.setDisplay();
        builder.setKeyboard();
        builder.setUsbCount();
        return builder.getCompute();
    }
}
