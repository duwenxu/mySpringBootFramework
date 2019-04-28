package com.example.SpringBoot.xiabao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

public class InInsuranceSecurityPlan {
    /**
     * 保证计划id,主键
     */
 
    private String securityPlanId;

    /**
     * 保障计划编号,由供应商提供，可能重复
     */
    private String securityPlanCode;

    /**
     * 保险产品编号，保险产品列表ne_insuranceinfo中的主键
     */
    private String insuranceCode;

    /**
     * 成本（元）
     */
    private BigDecimal cost;

    /**
     * 售价（元）
     */
    private BigDecimal price;

    /**
     * 保障期限开始时间（保障期限开始时间和保障期限结束时间组合使用）
     */
    private Integer guaranteePeriodStart;

    /**
     * 保障期限结束时间
     */
    private Integer guaranteePeriodEnd;

    /**
     * 保障期限时间 和开始、结束时间互斥
     */
    private Integer guaranteePeriod;

    /**
     * 获取保证计划id,主键
     *
     * @return security_plan_id - 保证计划id,主键
     */
    public String getSecurityPlanId() {
        return securityPlanId;
    }

    /**
     * 设置保证计划id,主键
     *
     * @param securityPlanId 保证计划id,主键
     */
    public void setSecurityPlanId(String securityPlanId) {
        this.securityPlanId = securityPlanId == null ? null : securityPlanId.trim();
    }

    /**
     * 获取保障计划编号,由供应商提供，可能重复
     *
     * @return security_plan_code - 保障计划编号,由供应商提供，可能重复
     */
    public String getSecurityPlanCode() {
        return securityPlanCode;
    }

    /**
     * 设置保障计划编号,由供应商提供，可能重复
     *
     * @param securityPlanCode 保障计划编号,由供应商提供，可能重复
     */
    public void setSecurityPlanCode(String securityPlanCode) {
        this.securityPlanCode = securityPlanCode == null ? null : securityPlanCode.trim();
    }

    /**
     * 获取保险产品编号，保险产品列表ne_insuranceinfo中的主键
     *
     * @return insurance_code - 保险产品编号，保险产品列表ne_insuranceinfo中的主键
     */
    public String getInsuranceCode() {
        return insuranceCode;
    }

    /**
     * 设置保险产品编号，保险产品列表ne_insuranceinfo中的主键
     *
     * @param insuranceCode 保险产品编号，保险产品列表ne_insuranceinfo中的主键
     */
    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode == null ? null : insuranceCode.trim();
    }

    /**
     * 获取成本（元）
     *
     * @return cost - 成本（元）
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置成本（元）
     *
     * @param cost 成本（元）
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 获取售价（元）
     *
     * @return price - 售价（元）
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置售价（元）
     *
     * @param price 售价（元）
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取保障期限开始时间（保障期限开始时间和保障期限结束时间组合使用）
     *
     * @return guarantee_period_start - 保障期限开始时间（保障期限开始时间和保障期限结束时间组合使用）
     */
    public Integer getGuaranteePeriodStart() {
        return guaranteePeriodStart;
    }

    /**
     * 设置保障期限开始时间（保障期限开始时间和保障期限结束时间组合使用）
     *
     * @param guaranteePeriodStart 保障期限开始时间（保障期限开始时间和保障期限结束时间组合使用）
     */
    public void setGuaranteePeriodStart(Integer guaranteePeriodStart) {
        this.guaranteePeriodStart = guaranteePeriodStart;
    }

    /**
     * 获取保障期限结束时间
     *
     * @return guarantee_period_end - 保障期限结束时间
     */
    public Integer getGuaranteePeriodEnd() {
        return guaranteePeriodEnd;
    }

    /**
     * 设置保障期限结束时间
     *
     * @param guaranteePeriodEnd 保障期限结束时间
     */
    public void setGuaranteePeriodEnd(Integer guaranteePeriodEnd) {
        this.guaranteePeriodEnd = guaranteePeriodEnd;
    }

    /**
     * 获取保障期限时间 和开始、结束时间互斥
     *
     * @return guarantee_period - 保障期限时间 和开始、结束时间互斥
     */
    public Integer getGuaranteePeriod() {
        return guaranteePeriod;
    }

    /**
     * 设置保障期限时间 和开始、结束时间互斥
     *
     * @param guaranteePeriod 保障期限时间 和开始、结束时间互斥
     */
    public void setGuaranteePeriod(Integer guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }
}