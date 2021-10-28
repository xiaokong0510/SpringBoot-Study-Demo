package com.xiao.mybatis;

import com.xiao.mybatis.mapper.UserMapper;
import com.xiao.mybatis.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author KongXiao
 * @date 2021/10/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    @Rollback
    public void test(){
        User user = User.builder()
                .name("zhangsan")
                .phoneNumber("12345678901")
                .status((byte) 1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.add(user);
        List<User> users = userMapper.selectAll();
        Assert.assertEquals(1,users.size());
        Assert.assertEquals("zhangsan",users.get(0).getName());
    }
}
