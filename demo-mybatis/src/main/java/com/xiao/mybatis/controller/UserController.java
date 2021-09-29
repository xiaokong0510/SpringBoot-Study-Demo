package com.xiao.mybatis.controller;

import com.xiao.mybatis.common.ApiResponse;
import com.xiao.mybatis.pojo.User;
import com.xiao.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Classname UserController
 * @Description 用户控制层
 * @Date 2021/1/26
 * @Author KongX
 * @Version: 1.0.0
 */

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("/userList")
    public ApiResponse userList() {
        List<User> userList = userService.getUserList();
        return ApiResponse.ok(userList);
    }

    /**
     * 根据id获取用户信息
     *
     * @return
     */
    @GetMapping("/{id}")
    public ApiResponse user(@PathVariable("id") Integer id) {
        User user = userService.selectById(id);
        return ApiResponse.ok(user);
    }

    /**
     * 新增
     *
     * @param user
     * @return
     */
    @PutMapping
    public ApiResponse add(@RequestBody User user) {
        int count = userService.add(user);
        return count > 0 ? ApiResponse.ok() : ApiResponse.error();
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @PostMapping
    public ApiResponse update(@RequestBody User user) {
        int count = userService.update(user);
        return count > 0 ? ApiResponse.ok() : ApiResponse.error();
    }

    /**
     * 删除
     *
     * @param id 用户id
     * @return
     */
    @DeleteMapping("/{id}")
    public ApiResponse update(@PathVariable("id") Integer id) {
        int count = userService.deleteById(id);
        return count > 0 ? ApiResponse.ok() : ApiResponse.error();
    }
}
