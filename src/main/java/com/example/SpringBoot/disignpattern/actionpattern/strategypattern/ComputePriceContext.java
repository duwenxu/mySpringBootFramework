package com.example.springboot.disignpattern.actionpattern.strategypattern;

/**
 * 价格策略的上下文
 *
 * @author duwenxu
 * @create 2020-07-04 19:16
 */
public class ComputePriceContext {

    private AbstractStrategy strategy;

    public AbstractStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    public double compute(double price){
        return this.strategy.compute(price);
    }
}
