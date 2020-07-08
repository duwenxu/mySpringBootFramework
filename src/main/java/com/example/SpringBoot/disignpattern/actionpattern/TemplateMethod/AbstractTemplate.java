package com.example.springboot.disignpattern.actionpattern.TemplateMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象模板
 *
 * @author duwenxu
 * @create 2020-07-05 16:40
 */
@Slf4j
public abstract class AbstractTemplate {

    public int state;

    /**
     * 模板方法
     */
    public void templateMethod(int param) {
        abstractMethod(param);
        hookMethod();
        concreteMethod();
    }

    //基本方法

    /**
     * 抽象方法
     */
    protected abstract void abstractMethod(int param);

    /**
     * 钩子方法   这里先空实现
     */
    protected void hookMethod() {
    }

    /**
     * 在抽象类中实现的具体方法
     */
    private void concreteMethod() {
        log.error("具体方法执行！state = "+ this.state);
    }


}
