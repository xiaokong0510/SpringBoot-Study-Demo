package com.xiao.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Set;

/**
 * 角色实体类
 * @author KongXiao
 * @date 2021/7/5
 */
@Data
@AllArgsConstructor
public class Role {
    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permission> permissions;
}
