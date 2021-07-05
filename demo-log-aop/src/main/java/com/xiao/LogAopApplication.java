package com.xiao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Classname LogAopApplication
 * @Description TODO
 * @Date 2021/1/18
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
public class LogAopApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogAopApplication.class, args);
        System.out.println("-----service start success-----");
    }
}
