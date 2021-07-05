package com.xiao.demo.shiro.service.impl;

import com.xiao.demo.shiro.entity.Permission;
import com.xiao.demo.shiro.entity.Role;
import com.xiao.demo.shiro.entity.User;
import com.xiao.demo.shiro.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author KongXiao
 * @date 2021/7/5
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public User getUserByName(String userName) {
        return getMapByName(userName);
    }

    /**
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private User getMapByName(String userName) {

        // admin role 具有查询权限和新增权限
        Permission queryPermission = new Permission("1", "query");
        Permission addPermission = new Permission("2", "add");
        Set<Permission> adminPermissionsSet = new HashSet<>();
        adminPermissionsSet.add(queryPermission);
        adminPermissionsSet.add(addPermission);
        Role adminRole = new Role("1", "admin", adminPermissionsSet);

        // user role具有查询权限
        Set<Permission> userPermissionsSet = new HashSet<>();
        userPermissionsSet.add(queryPermission);
        Role userRole = new Role("2", "user", userPermissionsSet);

        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(adminRole);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(userRole);

        User user = new User("1", "wsl", "123456", roleSet1);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUserName(), user);

        User user1 = new User("2", "zhangsan", "123456", roleSet2);
        map.put(user1.getUserName(), user1);
        return map.get(userName);
    }
}
