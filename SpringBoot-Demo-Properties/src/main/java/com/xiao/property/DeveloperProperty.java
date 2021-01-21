package com.xiao.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Classname Development
 * @Description 开发者信息配置，通过 @Value注解注入单个属性
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@Data
@Component
public class DeveloperProperty {
    /**
     * 通过配置文件注入
     */
    @Value("${developer.name}")
    private String name;
    @Value("${developer.phone-number}")
    private String phoneNumber;
}
