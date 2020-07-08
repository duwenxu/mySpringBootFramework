package com.example.springboot.disignpattern.createpattern.prototype;

/**
 * 原型实现类
 * @author duwenxu
 * @create 2020-07-01 15:14
 */
public class MacCompute extends Compute{

    private CPU cpu;

    public MacCompute() {
    }

    public MacCompute(CPU cpu) {
        this.cpu = cpu;
    }

    public void show(){
        System.out.println("原型实现类！");
    }

}
