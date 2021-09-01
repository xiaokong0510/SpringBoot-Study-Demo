package com.xiao.controller;

import cn.hutool.core.lang.Dict;
import com.xiao.property.DeveloperProperty;
import com.xiao.property.UserInfoProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname PropertiesController
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
@EnableConfigurationProperties(UserInfoProperty.class)
public class PropertiesController {

    private final DeveloperProperty developerProperty;

    private final UserInfoProperty userInfoProperty;

    @Autowired
    public PropertiesController(DeveloperProperty developerProperty, UserInfoProperty userInfoProperty) {
        this.developerProperty = developerProperty;
        this.userInfoProperty = userInfoProperty;
    }


    @RequestMapping("/property")
    public Dict getProperties() {
        return Dict.create().set("userInfoProperty", userInfoProperty).set("developerProperty", developerProperty);
    }

}
