package com.example.springboot.disignpattern.createpattern.prototype;

/**
 *
 * 抽象原型类
 * @author duwenxu
 * @create 2020-07-01 14:03
 */
public class Compute implements Cloneable {

    private CPU cpu;

    public Compute() {
    }

    public Compute(CPU cpu) {
        this.cpu = cpu;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return "Compute{" +
                "cpu=" + cpu.name +
                '}';
    }

    @Override
    protected Compute clone(){
        Compute compute = null;
        try {
            compute = (Compute)super.clone();
            compute.cpu = (CPU) this.cpu.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return compute;
    }

}
