package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname ValidationApplication
 * @Description TODO
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
public class ValidationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidationApplication.class,args);
        System.out.println("-----service start success-----");
    }
}
