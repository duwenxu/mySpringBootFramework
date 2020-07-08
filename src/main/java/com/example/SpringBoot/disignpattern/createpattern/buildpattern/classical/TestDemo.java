package com.example.springboot.disignpattern.createpattern.buildpattern.classical;

import com.example.springboot.disignpattern.createpattern.buildpattern.classical.builders.LenoveBuilder;
import com.example.springboot.disignpattern.createpattern.buildpattern.classical.builders.MacBuilder;

/**
 * @author duwenxu
 * @create 2020-06-30 14:42
 */
public class TestDemo {

    public static void main(String[] args) {
        ComputeDirector director = new ComputeDirector();

        Builder macBuilder = new MacBuilder("A12", "ddr4");
        Builder lenoveBuilder = new LenoveBuilder("amd", "ddr4");
        Compute mac = director.construct(macBuilder);
        Compute lenove = director.construct(lenoveBuilder);

        System.out.println(mac.toString());
        System.out.println(lenove.toString());

    }
}
