package com.example.springboot.jpa.repository;

import com.example.springboot.springsecurity.model.rbac_model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA(Java Persistence API)是Sun官方提出的Java持久化规范。是 Spring 基于 ORM 框架、JPA 规范的基础上封装的一套JPA应用框架，可使开发者用极简的代码即可实现对数据的访问和操作。它提供了包括增删改查等在内的常用功能，且易于扩展
 */

//dao只要继承JpaRepository类就可以，几乎可以不用写方法
// 还有一个特别有尿性的功能非常赞，就是可以根据方法名来自动的生产SQL，比如findByUserName 会自动生产一个以 userName 为参数的查询方法，比如 findAll 自动会查询表里面的所有数据，比如自动分页等等

/**
 * Spring Data JPA中的数据库接口：
 * PagingAndSortingRepository 继承 CrudRepository
 * 　　JpaRepository 继承 PagingAndSortingRepository
 */
@Component
public interface UserDao extends JpaRepository<User, Integer> {

    //简单(DataJPA)内置查询
    List<User> findByAddress(String address);

    List<User> findAllByAddress(String address);

    User findById(int id);

    //对与like模糊查询，若在此处直接设定参数为String，则调用方法时传入参数为： "%参数值%"
    List<User> findByAddressIsLikeAndNameEqualsAndAgeBetween(String address, String name, int between, int and);

    /**
     * @Query注解 查询适用于所查询的数据无法通过关键字查询得到结果的查询
     * @Query 有索引参数与命名参数两种方式
     * 含有LIKE关键字的查询：
     * 方式1：可以在占位符上添加"%",这样在查询方法中就不用添加"%"
     * 方式2：不在占位符上添加"%",这样就必须在查询方法的参数上添加"%"。
     * 设置nativeQuery=true   即可以使用原生的SQL进行查询
     * @Modifying注解 1、在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作。
     * 　　　　2、UPDATE或者DELETE操作需要使用事务，此时需要 定义Service层，在Service层的方法上添加事务操作。
     * 　　　　3、注意JPQL不支持INSERT操作。
     * @Modifying需要和@Transactional配合使用才能正常使用 原因：
     * 实质上@Modifying只是声明了这个操作是一个修改操作，但却没有修改这个方法的事务等级，因此这个方法依然不能进行修改操作。只有声明了@Transactional，本质上是声明了@Transactional(readOnly=false)，这样覆盖了默认的@Transactional配置便可以执行修改操作了
     * @Transactional注解 源码中：SimpleJpaRepository在类级别定义了@Transactional(readOnly = true)，而在和save、delete相关的操作重写了@Transactional属性（重写默认的readOnly属性是false），其余查询操作readOnly仍然为true
     */

    //自定义SQL语句查询

    //根据name删除
    @Transactional
    @Query(value = "delete from USER where name =?1 ", nativeQuery = true)
    @Modifying
    public void deleteUserByName(String name);

    //按条件修改操作
    @Transactional
    @Query(value = "update USER u set u.name=?1 where u.address=?2 ", nativeQuery = true)
    @Modifying
    public void updateUserByNameAndAddress(String name, String address);

    //按id修改
    @Transactional
    @Query(value = "update USER u set u.name=?1 ,u.age=?2,u.address=?3 where u.id=?4 ", nativeQuery = true)
    @Modifying
    public void updateByPrimaryKey(String name, int age, String address, int id);


    //分页查询

    /**
     * spring data jpa已经帮我们实现了分页的功能，在查询的方法中，需要传入参数Pageable ,
     * 当查询中有多个参数的时候Pageable建议做为最后一个参数传入
     * <p>
     * Page<User> findALL(Pageable pageable);
     * Page<User> findByUserName(String userName,Pageable pageable);
     * Pageable 是spring封装的分页实现类，使用的时候需要传入页数、每页条数和排序规则
     */
    Page<User> findByName(String username, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    User findByName(String username);


    //多表查询

/**
 *多表查询在spring data jpa中有两种实现方式：
 *          第一种是利用hibernate的级联查询来实现
 *          第二种是创建一个结果集的接口来接收连表查询后的结果，这里主要第二种方式.  其实还是使用native sql 实现查询，然后用自定义的类来接收查询后的结果
 */



}
