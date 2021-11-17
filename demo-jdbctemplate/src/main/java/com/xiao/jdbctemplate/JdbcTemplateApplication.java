package com.xiao.jdbctemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author KongXiao
 * @date 2021/10/28
 */
@SpringBootApplication
public class JdbcTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApplication.class);
        System.out.println("--------service start success ------");
    }
}
