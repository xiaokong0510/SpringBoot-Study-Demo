package com.xiao.shiro.service;

import com.xiao.shiro.entity.User;

/**
 * @author KongXiao
 * @date 2021/7/5
 */
public interface LoginService {
    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByName(String userName);
}
