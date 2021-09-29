package com.xiao.mybatis.service;

import com.xiao.mybatis.pojo.User;

import java.util.List;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2021/1/26
 * @Author KongX
 * @Version: 1.0.0
 */
public interface UserService {
    /**
     * 获取用户列表
     * @return 用户信息列表
     */
    List<User> getUserList();

    /**
     * 根据id查询
     * @param id 用户id
     * @return 用户信息
     */
    User selectById(Integer id);

    /**
     * 新增用户信息
     * @param model 用户信息
     * @return 成功 ：1； 失败 ： 0
     */
    int add(User model);

    /**
     * 修改用户信息
     * @param model 用户信息
     * @return 成功 ：1； 失败 ： 0
     */
    int update(User model);


    /**
     * 删除用户
     * @param id 用户id
     * @return 成功 ：1； 失败 ： 0
     */
    int deleteById(Integer id);
}
