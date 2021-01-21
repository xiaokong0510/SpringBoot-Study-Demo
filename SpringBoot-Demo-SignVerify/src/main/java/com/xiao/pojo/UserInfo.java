package com.xiao.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname UserInfo
 * @Description TODO
 * @Date 2020/10/30
 * @Author KongX
 * @version: 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userName;
    private Integer age;
}
