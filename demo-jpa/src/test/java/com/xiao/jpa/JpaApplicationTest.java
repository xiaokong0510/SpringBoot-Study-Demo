package com.xiao.jpa;

import com.xiao.jpa.entity.User;
import com.xiao.jpa.repository.UserRepository;
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
import java.util.Optional;

/**
 * @author KongXiao
 * @date 2021/10/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTest {

    @Autowired
    private UserRepository userRepository;
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
        User save = userRepository.save(user);
        User userByName = userRepository.findByName(name);
        Assert.assertEquals(phoneNumber, userByName.getPhoneNumber());
        Optional<User> byId = userRepository.findById(save.getId());
        Assert.assertTrue(byId.isPresent());
        Assert.assertEquals(phoneNumber, byId.get().getPhoneNumber());
    }
}
