package com.xiao.mybatis;

import com.xiao.mybatis.mapper.UserMapper;
import com.xiao.mybatis.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author KongXiao
 * @date 2021/10/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;
    private User user;

    @Before
    public void setUp() {
        this.user = initUser();
    }

    private final static String name = "zhangsan";
    private final static String phoneNumber = "12345678901";

    private User initUser() {
        return User.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .status((byte) 1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAndSelectAll() {
        userMapper.add(user);
        List<User> users = userMapper.selectAll();
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(name, users.get(0).getName());
        User queryUser = userMapper.selectById(this.user.getId());
        Assert.assertNotNull(queryUser);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateAndDelete() {
        userMapper.add(user);
        Long userId = user.getId();
        User queryUser = userMapper.selectById(userId);
        String newName = "lisi";
        queryUser.setName(newName);
        userMapper.update(queryUser);
        Assert.assertEquals(newName, userMapper.selectById(userId).getName());
        userMapper.deleteById(userId);
        Assert.assertEquals((byte) 0, (Object) userMapper.selectById(userId).getStatus());
    }
}
