package com.xiao.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname MybatisApplication
 * @Description TODO
 * @Date 2021/1/25
 * @Author KongX
 * @Version: 1.0.0
 */
@SpringBootApplication
@MapperScan("com.xiao.mybatis.mapper")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class);
        System.out.println("--------service start success ------");
    }
}
