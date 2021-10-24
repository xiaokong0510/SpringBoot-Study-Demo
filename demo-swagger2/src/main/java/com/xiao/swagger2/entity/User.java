package com.xiao.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author KongXiao
 * @date 2021/10/14
 */
@Data
@ApiModel(value = "User", description = "用户信息")
public class User implements Serializable {
    private static final long serialVersionUID = -7478329642374504050L;
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("用户名")
    private String username;
}
