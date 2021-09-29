package com.xiao.log.aop.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xiao.log.aop.aspect.RequestInfo;
import com.xiao.log.aop.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description TODO
 * @Date 2021/1/18
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/test")
    public Dict test(@RequestParam("userName") String userName) {
        return Dict.create().set("who", StrUtil.isBlank(userName) ? "me" : userName);
    }

    @RequestMapping("/test02")
    public Dict test02(@RequestBody UserInfo user) {
        return Dict.create().set("who", StrUtil.isBlank(user.getUserName()) ? "me" : user.getUserName())
                .set("age", ObjectUtil.isEmpty(user.getAge()) ? "250" : user.getAge());
    }
}
