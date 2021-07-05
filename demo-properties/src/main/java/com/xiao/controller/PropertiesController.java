package com.xiao.controller;

import cn.hutool.core.lang.Dict;
import com.xiao.property.DeveloperProperty;
import com.xiao.property.UserInfoProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname PropertiesController
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
public class PropertiesController {

    @Autowired
    private DeveloperProperty developerProperty;
    @Autowired
    private UserInfoProperty userInfoProperty;

    @RequestMapping("/property")
    public Dict getProperties() {
       return Dict.create().set("userInfoProperty", userInfoProperty).set("developerProperty", developerProperty);
    }

}
