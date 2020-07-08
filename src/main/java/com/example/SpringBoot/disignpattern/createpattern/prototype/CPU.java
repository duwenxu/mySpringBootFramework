package com.example.springboot.disignpattern.createpattern.prototype;

/**
 * @author duwenxu
 * @create 2020-07-01 14:58
 */
public class CPU implements Cloneable{

    public String name;

    public CPU(String name) {
        this.name = name;
    }

    @Override
    protected Object clone(){
        CPU cpu = null;

        try {
            cpu = (CPU) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cpu;
    }
}
