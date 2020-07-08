package com.example.springboot.disignpattern.actionpattern.strategypattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 普通会员策略
 *
 * @author duwenxu
 * @create 2020-07-04 19:18
 */
@Slf4j
public class CommonStrategy extends AbstractStrategy {

    @Override
    public Double compute(Double price) {
        log.info("普通会员没有折扣！");
        return price;
    }
}
