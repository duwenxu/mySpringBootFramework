package com.example.springboot.disignpattern.actionpattern.strategypattern;

import com.example.springboot.disignpattern.actionpattern.strategypattern.concretestrategy.Vip0Strategy;
import com.example.springboot.disignpattern.actionpattern.strategypattern.concretestrategy.Vip1Strategy;
import com.example.springboot.disignpattern.actionpattern.strategypattern.concretestrategy.Vip2Startegy;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *   策略模式测试
 * @author duwenxu
 * @create 2020-07-04 19:41
 */
@Slf4j
public class TestDemo {

    public static void main(String[] args) {
        AbstractStrategy vip0Strategy = new Vip0Strategy();
        Vip1Strategy vip1Strategy = new Vip1Strategy();
        Vip2Startegy vip2Startegy = new Vip2Startegy();

        ComputePriceContext priceContext = new ComputePriceContext();
        priceContext.setStrategy(vip0Strategy);
        System.out.println(priceContext.compute(100));

        priceContext.setStrategy(vip1Strategy);
        System.out.println(priceContext.compute(100));

        priceContext.setStrategy(vip2Startegy);
        System.out.println(priceContext.compute(100));
    }

}
