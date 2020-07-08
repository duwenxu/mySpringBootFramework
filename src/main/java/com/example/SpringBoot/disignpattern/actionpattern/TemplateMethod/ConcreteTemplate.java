package com.example.springboot.disignpattern.actionpattern.TemplateMethod;

/**
 *  具体模板方法
 * @author duwenxu
 * @create 2020-07-05 16:45
 */
public class ConcreteTemplate extends AbstractTemplate{

    public ConcreteTemplate() {
    }

    @Override
    protected void abstractMethod(int param) {
        this.state = param;
    }

    @Override
    protected void hookMethod() {
        this.state+= 1;
    }
}
