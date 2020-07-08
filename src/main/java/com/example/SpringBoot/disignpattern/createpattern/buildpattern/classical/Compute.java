package com.example.springboot.disignpattern.createpattern.buildpattern.classical;

/**
 *
 * 产品实例
 * @author duwenxu
 * @create 2020-06-30 14:24
 */
public class Compute {

    private String cpu;
    private String ram;
    private int usbCount;
    private String keyboard;
    private String display;


    public Compute(String cpu, String ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    public void setUsbCount(int usbCount) {
        this.usbCount = usbCount;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public void setDisplay(String display) {
        this.display = display;
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
}
