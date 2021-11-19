package com.xiao.jpa.multidatasource;

import com.xiao.jpa.multidatasource.entity.primary.User;
import com.xiao.jpa.multidatasource.entity.secondary.Message;
import com.xiao.jpa.multidatasource.repository.primary.UserRepository;
import com.xiao.jpa.multidatasource.repository.secondary.MessageRepository;
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
 * @date 2021/11/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMultiDataSourceApplicationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    private User user;
    private Message message;

    @Before
    public void setUp() {
        this.user = initUser();
        this.message = initMessage();
    }

    private final static String name = "zhangsan";
    private final static String phoneNumber = "12345678901";
    private final static String content = "hello";

    private User initUser() {
        return User.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .status((byte) 1)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    private Message initMessage() {
        return Message.builder()
                .content(content)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    @Test
    @Transactional(transactionManager = "secondaryTransactionManager")
    @Rollback
    public void test() {
        User userSave = userRepository.save(user);
        Optional<User> userById = userRepository.findById(userSave.getId());
        Assert.assertTrue(userById.isPresent());
        Assert.assertEquals(phoneNumber, userById.get().getPhoneNumber());

        Message messageSave = messageRepository.save(message);
        Optional<Message> messageById = messageRepository.findById(messageSave.getId());
        Assert.assertTrue(messageById.isPresent());
        Assert.assertEquals(content, messageById.get().getContent());

    }
}
