package com.xiao.controller;

import com.xiao.anno.SignVerify;
import com.xiao.pojo.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/10/30
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
@RequestMapping(value = "/signAuth", produces = "application/json;charset=UTF-8")
public class SignVerifyController {

    @SignVerify
    @RequestMapping("/signDemo01")
    public String demo(@RequestParam String userName, @RequestParam Integer age) {
        return "hello!";
    }

    @SignVerify
    @RequestMapping("/signDemo02")
    public String demo(@RequestBody UserInfo model) {
        return "hello!";
    }

    @SignVerify
    @RequestMapping("/signDemo03")
    public String demo(@RequestParam String address, @RequestBody UserInfo model) {
        return "hello!";
    }
}
