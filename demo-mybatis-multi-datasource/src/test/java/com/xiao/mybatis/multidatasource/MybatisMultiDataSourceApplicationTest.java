package com.xiao.mybatis.multidatasource;

import com.xiao.mybatis.multidatasource.entity.User;
import com.xiao.mybatis.multidatasource.mapper.primary.UserMapperPrimary;
import com.xiao.mybatis.multidatasource.mapper.secondary.UserMapperSecondary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author KongXiao
 * @date 2021/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisMultiDataSourceApplicationTest {
    @Autowired
    private UserMapperPrimary userMapperPrimary;
    @Autowired
    private UserMapperSecondary userMapperSecondary;

    private User user;
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

    @Before
    public void setUp() {
        // 清空测试表，保证每次结果一样
        userMapperPrimary.deleteAll();
        userMapperSecondary.deleteAll();
        this.user = initUser();
    }

    @Test
    public void test() throws Exception {
        // 往Primary数据源插入一条数据
        userMapperPrimary.add(user);

        User userPrimary = userMapperPrimary.selectByName(name);
        Assert.assertEquals(phoneNumber, userPrimary.getPhoneNumber());
        User userSecondary = userMapperSecondary.selectByName(name);
        Assert.assertNull(userSecondary);


        userMapperSecondary.add(user);

        // 从Secondary数据源查询刚才插入的数据，配置正确就可以查询到
        userSecondary = userMapperSecondary.selectByName(name);
        Assert.assertEquals(phoneNumber, userSecondary.getPhoneNumber());
    }

}
