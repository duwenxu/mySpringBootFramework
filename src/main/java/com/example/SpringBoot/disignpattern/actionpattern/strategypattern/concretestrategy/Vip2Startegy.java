package com.example.springboot.disignpattern.actionpattern.strategypattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 二级会员用户
 *
 * @author duwenxu
 * @create 2020-07-04 19:35
 */
@Slf4j
public class Vip2Startegy extends AbstractStrategy {

    @Override
    public double compute(double price) {
        log.info("二级用户打9折！");
        return price*0.9;
    }
}
