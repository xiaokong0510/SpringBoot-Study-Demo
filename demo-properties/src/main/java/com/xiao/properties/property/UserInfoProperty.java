package com.xiao.properties.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * @Classname UserInfo
 * @Description 用户信息配置，通过@ConfigurationProperties注解，配置文件前缀user注入属性
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "user")
public class UserInfoProperty {

    private String username;
    private Integer age;
    private List<Object> lists;
    private Map<String, Object> maps;
}
