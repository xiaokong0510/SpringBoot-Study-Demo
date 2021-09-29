package com.xiao.mybatis.service.impl;

import com.xiao.mybatis.mapper.UserMapper;
import com.xiao.mybatis.pojo.User;
import com.xiao.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2021/1/26
 * @Author KongX
 * @Version: 1.0.0
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     *
     * @return
     */
    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int add(User model) {
        return userMapper.add(model);
    }

    @Override
    public int update(User model) {
        return userMapper.update(model);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }
}
