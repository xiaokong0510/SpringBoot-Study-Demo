package com.xiao.controller;

import com.xiao.response.CommonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@RestController
@Validated
public class UserController {


    @GetMapping("/user")
    public CommonResult get(@NotNull Integer id, @NotNull String name) {
        return CommonResult.success(null, "操作成功！");

    }

}
