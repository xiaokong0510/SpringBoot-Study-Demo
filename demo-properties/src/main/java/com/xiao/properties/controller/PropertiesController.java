package com.xiao.properties.controller;

import cn.hutool.core.lang.Dict;
import com.xiao.properties.property.DeveloperProperty;
import com.xiao.properties.property.UserInfoProperty;
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

    public PropertiesController(DeveloperProperty developerProperty, UserInfoProperty userInfoProperty) {
        this.developerProperty = developerProperty;
        this.userInfoProperty = userInfoProperty;
    }

    @RequestMapping("/property")
    public Dict getProperties() {
        return Dict.create().set("userInfoProperty", userInfoProperty).set("developerProperty", developerProperty);
    }

}
