package com.xiao.demo.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 权限实体类
 * @author KongXiao
 * @date 2021/7/5
 */
@Data
@AllArgsConstructor
public class Permission {
    private String id;
    private String permissionName;
}
