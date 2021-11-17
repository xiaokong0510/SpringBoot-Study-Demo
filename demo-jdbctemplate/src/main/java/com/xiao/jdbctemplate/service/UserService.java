package com.xiao.jdbctemplate.service;

import com.xiao.jdbctemplate.entity.User;

import java.util.List;

/**
 * @author KongXiao
 * @date 2021/10/28
 */
public interface UserService {
    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    List<User> selectAll();

    /**
     * 根据用户名查询
     *
     * @param id 用户id
     * @return 用户信息
     */
    User selectById(Long id);

    /**
     * 新增用户信息
     *
     * @param model 用户信息
     * @return 主键id
     */
    Long add(User model);

    /**
     * 修改用户信息
     *
     * @param model 用户信息
     * @return 成功 ：1； 失败 ： 0
     */
    int update(User model);


    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 成功 ：1； 失败 ： 0
     */
    int deleteById(Long id);
}
