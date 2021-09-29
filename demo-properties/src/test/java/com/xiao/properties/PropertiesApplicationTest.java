package com.xiao.properties;

import com.xiao.properties.property.DeveloperProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author KongXiao
 * @date 2021/9/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class PropertiesApplicationTest {

    @Autowired
    private DeveloperProperty developerProperty;


    @Test
    void getHello() {
        Assertions.assertEquals("kongxiao@prod", developerProperty.getName());
        Assertions.assertEquals("xxxxxx@prod", developerProperty.getPhoneNumber());
    }

}