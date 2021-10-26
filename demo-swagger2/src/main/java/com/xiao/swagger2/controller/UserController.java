package com.xiao.swagger2.controller;

import com.xiao.swagger2.entity.CommonResult;
import com.xiao.swagger2.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2021/1/22
 * @Author KongX
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/users")
@Api(tags = "User",produces = "application/json")
public class UserController {

    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表",notes = "获取所有的用户信息列表")
    @ApiResponses({@ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常"),
            @ApiResponse(code = 401, message = "权限不足")})
    @GetMapping
    public CommonResult<List<User>> list() {
        List<User> userList = new ArrayList<>(UserController.users.values());
        return CommonResult.success(userList);
    }

    @ApiOperation(value = "获取单个用户信息", notes = "根据用户id查询")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    @GetMapping(value = "/{id}")
    public CommonResult<User> getUserById(@PathVariable("id") Integer id) {
        return CommonResult.success(users.get(id));
    }

    @ApiOperation(value = "创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细信息", required = true, dataType = "User")
    @PostMapping
    public CommonResult<User> createUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return CommonResult.success(user);
    }

    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细信息", required = true, dataType = "User"),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @PutMapping("/{id}")
    public CommonResult<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        User u = users.get(id);
        u.setUsername(user.getUsername());
        u.setAge(user.getId());
        users.put(user.getId(), user);
        return CommonResult.success(user);
    }

    @ApiOperation(value = "删除单个用户信息", notes = "根据用户id删除")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public CommonResult<User> deleteUserById(@PathVariable("id") Integer id) {
        users.remove(id);
        return CommonResult.success(users.get(id));
    }

}
