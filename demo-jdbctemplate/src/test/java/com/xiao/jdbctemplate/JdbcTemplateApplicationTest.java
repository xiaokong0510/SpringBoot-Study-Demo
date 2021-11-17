package com.xiao.jdbctemplate;

import com.xiao.jdbctemplate.entity.User;
import com.xiao.jdbctemplate.service.UserService;
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
public class JdbcTemplateApplicationTest {
    @Autowired
    private UserService userService;
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
        Long userId = userService.add(user);
        User user = userService.selectById(userId);
        Assert.assertEquals(phoneNumber, user.getPhoneNumber());
        List<User> users = userService.selectAll();
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(name, users.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAndUpdate() {
        Long userId = userService.add(user);
        User user = userService.selectById(userId);
        String newName = "lisi";
        user.setName(newName);
        userService.update(user);
        Assert.assertNotNull(userService.selectById(userId));
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() {
        Long userId = userService.add(user);
        userService.deleteById(userId);
        User user = userService.selectById(userId);
        Assert.assertEquals(Optional.of((byte) 0).get(), user.getStatus());

    }

}
