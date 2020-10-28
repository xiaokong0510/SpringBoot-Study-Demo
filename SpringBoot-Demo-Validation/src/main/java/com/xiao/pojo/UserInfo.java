package com.xiao.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserInfo
 * @Description 用户信息实体类
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@Data
public class UserInfo {
    @NotNull
    private String username;
    @NotNull
    private Integer roleId;
}
