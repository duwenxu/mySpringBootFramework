package com.example.springboot.disignpattern.createpattern.buildpattern.classical;

/**
 *
 *  抽象建造者类
 * @author duwenxu
 * @create 2020-06-30 14:25
 */
public abstract class Builder {

    public abstract void setUsbCount();

    public abstract void setKeyboard();

    public abstract void setDisplay();

    public abstract Compute getCompute();
}
