package com.example.springboot.DataBase_CRUD.service;

import com.example.SpringBoot.DataBase_CRUD.Entity.BaseBean;
import com.example.springboot.DataBase_CRUD.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 通用service接口
 */
public interface UserService {
    /**
     *通用查询all
     * @param <T>
     * @return
     */
    <T extends BaseBean> List<T> findList();

    /**
     * 通用按id查询
     * @param id
     * @param <T>
     * @return
     */
    <T extends BaseBean> T findUserById(int id);

    /**
     * 通用按name查询
     * @param name
     * @param <T>
     * @return
     */
    <T extends BaseBean> Page<User> findUserByName(String name);

    /**
     * 通用增加
     * @param t
     * @param <T>
     */
    <T extends BaseBean> void insert(T t);

    /**
     * 通用按主键修改
     * @param t
     * @param <T>
     */
    <T extends BaseBean> void updateByPrimaryKey(T t);

    /**
     * 通用按主键删除
     * @param t
     * @param <T>
     */
    <T extends BaseBean> void deleteByPrimaryKey(T t);




}
