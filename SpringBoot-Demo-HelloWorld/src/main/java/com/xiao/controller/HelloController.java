package com.xiao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(@RequestParam("userName") String userName) {
        return "Hello," + userName + "!";
    }
}
