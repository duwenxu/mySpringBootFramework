package com.example.springboot.disignpattern.actionpattern.TemplateMethod;

/**
 * @author duwenxu
 * @create 2020-07-05 16:52
 */
public class TestDemo {


    public static void main(String[] args) {
        ConcreteTemplate template = new ConcreteTemplate();
        for (int i = 0; i < 100; i+=3) {
            template.templateMethod(i);
        }
    }


}