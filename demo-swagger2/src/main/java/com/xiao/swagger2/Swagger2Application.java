package com.xiao.swagger2;

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
public class Swagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(Swagger2Application.class);
        System.out.println("-------start service at port 8084---------");
    }
}
