package com.xiao.mybatis.mapper;

import com.xiao.mybatis.MybatisApplicationTest;
import com.xiao.mybatis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Classname UserMapperTest
 * @Description TODO
 * @Date 2021/1/26
 * @Author KongX
 * @Version: 1.0.0
 */
@Slf4j
public class UserMapperTest extends MybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectAll() {
        List<User> userList = userMapper.selectAll();
        log.info("【userList】= {}", userList);
    }

}
