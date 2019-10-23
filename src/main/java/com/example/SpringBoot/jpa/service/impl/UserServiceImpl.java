package com.example.springboot.jpa.service.impl;

import com.example.springboot.jpa.repository.UserDao;
import com.example.springboot.springsecurity.model.rbac_model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl{
    @Autowired
    UserDao userDao;

    public List<User> findList() {
        return userDao.findAll();
    }

    public User findUserById(int id) {
        return userDao.findById(id);
    }

    public Page<User> findUserByName(String name) {
//        int pageNo=1,pageSize=10;
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
//        PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
//        return userDao.findByName(name, pageable);
        return userDao.findByName(name, new PageRequest(1, 10, new Sort(Sort.Direction.ASC, "id")));
    }

    public void insert(User user) {
        userDao.save(user);
    }

    public void updateByPrimaryKey(User user) {
        userDao.updateByPrimaryKey(user.getName(), user.getAge(), user.getAddress(), user.getId());
    }

    public void deleteByPrimaryKey(int id) {
        userDao.deleteById(id);
    }
}
