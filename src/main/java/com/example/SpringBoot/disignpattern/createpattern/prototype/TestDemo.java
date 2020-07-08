package com.example.springboot.disignpattern.createpattern.prototype;

/**
 * @author duwenxu
 * @create 2020-07-01 15:18
 */
public class TestDemo {

    public static void main(String[] args) {
        CPU cpu1 = new CPU("1060");
        CPU cpu2 = new CPU("1080");

        Compute compute1 = new MacCompute();
        compute1.setCpu(cpu1);

        Compute compute2 = compute1.clone();
        compute2.setCpu(cpu2);


        System.out.println(compute1.toString());
        System.out.println(compute2.toString());
    }
}
