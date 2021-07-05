package com.xiao.controller;

import com.xiao.pojo.Update;
import com.xiao.pojo.UserInfo;
import com.xiao.response.CommonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;


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


    @GetMapping("/test")
    public CommonResult test(@RequestParam("age") Integer age) {
        return CommonResult.success(null, "操作成功！");
    }

    @GetMapping("/test02")
    public CommonResult test02(@RequestParam("age") @NotNull(message = "age不能为空") Integer age) {
        return CommonResult.success(null, "操作成功！");
    }

    @GetMapping("/test03")
    public CommonResult test(@Valid UserInfo userInfo) {
        return CommonResult.success(null, "操作成功！");
    }

    @GetMapping("/test04")
    public CommonResult test04(@RequestBody @Valid UserInfo userInfo) {
        return CommonResult.success(null, "操作成功！");
    }

    @GetMapping("/test05")
    public CommonResult test05(@RequestBody @Validated(value = Update.class) UserInfo userInfo) {
        return CommonResult.success(null, "操作成功！");
    }
}
