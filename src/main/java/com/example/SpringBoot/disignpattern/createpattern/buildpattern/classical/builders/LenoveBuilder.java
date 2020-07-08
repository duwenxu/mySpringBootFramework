package com.example.springboot.disignpattern.createpattern.buildpattern.classical.builders;

import com.example.springboot.disignpattern.createpattern.buildpattern.classical.Builder;
import com.example.springboot.disignpattern.createpattern.buildpattern.classical.Compute;

/**
 *
 * 具体的建造者类
 * @author duwenxu
 * @create 2020-06-30 14:33
 */
public class LenoveBuilder extends Builder {

    private Compute compute;

    public LenoveBuilder(String cpu,String ram){
        this.compute = new Compute(cpu,ram);
    }

    @Override
    public void setUsbCount() {
        this.compute.setUsbCount(3);
    }

    @Override
    public void setKeyboard() {
        this.compute.setKeyboard("lenove keyboard");
    }

    @Override
    public void setDisplay() {
        this.compute.setDisplay("lenove display");
    }

    @Override
    public Compute getCompute() {
        return compute;
    }
}
