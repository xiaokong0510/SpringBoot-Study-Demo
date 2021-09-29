package com.xiao.helloworld.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(@RequestParam(required = false, name = "userName") String userName) {
       if(StrUtil.isBlank(userName)) {
           userName = "World";
       }
        return StrUtil.format("Hello,{}!", userName);
    }
}
