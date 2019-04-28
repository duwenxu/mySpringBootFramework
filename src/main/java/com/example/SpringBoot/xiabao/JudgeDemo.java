package com.example.SpringBoot.xiabao;

import javafx.util.Pair;
import org.junit.platform.commons.util.StringUtils;

import java.util.List;

/**
 * 判断
 *
 * @author duwenxu
 * @create 2019-03-12 17:24
 */
public class JudgeDemo {

//    //可以把  保障期限开始和结束时间定义为一个   Pair<startTime,endTime>
//    public void veifyInSecurityPlan(List<InInsuranceSecurityPlan> planInfos) {
//        Integer period = 0, end = 0, start;
//        Pair<Integer, Integer> timePair;
//        for (int i = 0; i < planInfos.size(); i++) {
//            timePair = new Pair<>(period, end);
//            InInsuranceSecurityPlan planInfo = planInfos.get(i);
//            period = planInfo.getGuaranteePeriod();
//            end = planInfo.getGuaranteePeriodEnd();
//            start = planInfo.getGuaranteePeriodStart();
//            if (timeVerify(i, period, start, end)) {
//                if (compareValue(period, start) < compareValue(timePair.getKey(), timePair.getValue())) {
//                    throw new BusinessException("第" + (i + 1) + "条保障计划信息异常:下一条的保障时间不能小于上一条！");
//                }
//            }
//        }
//    }
//
//    public static Boolean timeVerify(int i, Integer period, Integer start, Integer end) {
//        if (isBlack(period) && (isBlack(end) || isBlack(start))) {
//            throw new BusinessException("第" + (i + 1) + "条保障计划信息异常:保障期限不能为空！");
//        }
//        if (!isBlack(period) && (!isBlack(end) || !isBlack(start))) {
//            throw new BusinessException("第" + (i + 1) + "条保障计划信息异常:只能有一个保障期限！");
//        }
//        if (isBlack(period) && (!isBlack(end) && !isBlack(start)) && (end < start)) {
//            throw new BusinessException("第" + (i + 1) + "条保障计划信息异常:保障开始日期不能大于结束日期！");
//        }
//        return true;
//    }
//
//    public static Integer compareValue(Integer a, Integer b) {
//        if (a == null) {
//            return b;
//        }
//        return a;
//    }
//
//    public static Boolean isBlack(Integer value) {
//        return StringUtils.isBlank(String.valueOf(value));
//    }
}
