package com.xiao.validation.pojo;

import com.xiao.validation.anno.HaveNoBlank;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "userName不能为空",groups = Update.class)
    @HaveNoBlank
    private String userName;

    @NotNull(message = "age不能为空")
    @Max(value = 10, message = "age最大值为10")
    @Min(value = 1, message = "age最小值为1")
    private Integer age;
}
