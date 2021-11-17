package com.xiao.jdbctemplate.mutildatasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author KongXiao
 * @date 2021/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcMultiDataSourceApplicationTest {

    // 不用@Qualifier指定指定的时候，会采用参数的名字来查找Bean，存在的话就注入。
    @Autowired
    protected JdbcTemplate primaryJdbcTemplate;

    @Autowired
    protected JdbcTemplate secondaryJdbcTemplate;

    @Before
    public void setUp() {
        primaryJdbcTemplate.update("DELETE  FROM  USER ");
        secondaryJdbcTemplate.update("DELETE  FROM  USER ");
    }

    @Test
    public void test() throws Exception {
        primaryJdbcTemplate.update("insert into user(name,phone_number) values(?, ?)", "zhangsan", "13555555555");
        secondaryJdbcTemplate.update("insert into user(name,phone_number) values(?, ?)", "lisi", "13888888888");

        Assert.assertEquals("1", primaryJdbcTemplate.queryForObject("select count(1) from user", String.class));

        // 查一下第一个数据源中是否有 1 条数据，验证插入是否成功
        Assert.assertEquals("1", secondaryJdbcTemplate.queryForObject("select count(1) from user", String.class));
    }

}
