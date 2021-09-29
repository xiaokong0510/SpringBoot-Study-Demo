package com.xiao.swaggger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname SwaggerApplicaton
 * @Description TODO
 * @Date 2021/1/22
 * @Author KongX
 * @Version: 1.0.0
 */
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class);
        System.out.println("-------start service at port 8086---------");
    }
}
