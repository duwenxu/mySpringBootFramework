package com.example.springboot.disignpattern.actionpattern.strategypattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 *  一级会员用户
 * @author duwenxu
 * @create 2020-07-04 19:29
 */

@Slf4j
public class Vip1Strategy extends AbstractStrategy{

    @Override
    public double compute(double price) {
        log.info("一级会员打8折");
        return price* 0.8;
    }
}
