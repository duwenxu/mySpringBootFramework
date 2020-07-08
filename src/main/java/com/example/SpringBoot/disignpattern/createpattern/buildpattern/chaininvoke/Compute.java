package com.example.springboot.disignpattern.createpattern.buildpattern.chaininvoke;

/**
 *
 * 建造者模式的   链式调用
 *
 *   传统的构造方式：
 *   1. 折叠构造    多个构造函数，需要哪个用哪个
 *   2。JavaBean    通过set填充属性
 *
 *  实现步骤：
 *      在Computer 中创建一个静态内部类 Builder，然后将Computer 中的参数都复制到Builder类中。
 *      在Computer中创建一个private的构造函数，参数为Builder类型
 *      在Builder中创建一个public的构造函数，参数为Computer中必填的那些参数，cpu 和ram。
 *      在Builder中创建设置函数，对Computer中那些可选参数进行赋值，返回值为Builder类型的实例
 *      在Builder中创建一个build()方法，在其中构建Computer的实例并返回
 *
 * @author duwenxu
 * @create 2020-06-30 13:48
 *
 */
public class Compute {

    private final String cpu;
    private final String ram;
    private final int usbCount;
    private final String keyboard;
    private final String display;

    private Compute(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.usbCount = builder.usbCount;
        this.keyboard = builder.keyboard;
        this.display = builder.display;
    }


    @Override
    public String toString() {
        return "Compute{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", usbCount=" + usbCount +
                ", keyboard='" + keyboard + '\'' +
                ", display='" + display + '\'' +
                '}';
    }

    /**
     * 可以使用类名直接调用该类的 static 对象
     */
    public static class Builder {

        private String cpu;
        private String ram;
        private int usbCount;
        private String keyboard;
        private String display;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        private Compute build() {
            return new Compute(this);
        }
    }


    public static void main(String[] args) {
        Compute compute = new Compute.Builder("", "")
                .setDisplay("")
                .setKeyboard("")
                .setUsbCount(3)
                .build();
        System.out.println(compute.toString());
    }
}
