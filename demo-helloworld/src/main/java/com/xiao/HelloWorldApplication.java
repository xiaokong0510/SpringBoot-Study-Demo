package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname com.xiao.HelloWorldApplication
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
        System.out.println("--------service start success ------");
    }
}
