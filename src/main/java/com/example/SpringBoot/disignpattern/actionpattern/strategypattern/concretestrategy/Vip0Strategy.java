package com.example.springboot.disignpattern.actionpattern.strategypattern.concretestrategy;

import com.example.springboot.disignpattern.actionpattern.strategypattern.AbstractStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * 普通会员策略
 *
 * @author duwenxu
 * @create 2020-07-04 19:18
 */
@Slf4j
public class Vip0Strategy extends AbstractStrategy {

    @Override
    public double compute(double price) {
        log.error("普通会员没有折扣！");
        return price;
    }
}
