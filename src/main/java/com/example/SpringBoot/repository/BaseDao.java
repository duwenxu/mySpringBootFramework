package com.example.SpringBoot.repository;

import com.example.SpringBoot.Entity.BaseBean;

/**
 * 增删改查的基础接口
 */
public interface BaseDao {
    /**
     * 通用动态新增
     * @param record
     * @param <T>  在方法的返回值之前声明一个泛型T,可以自定义满足条件的泛型类型
     */
    <T extends BaseBean> void insert(T record);
}
