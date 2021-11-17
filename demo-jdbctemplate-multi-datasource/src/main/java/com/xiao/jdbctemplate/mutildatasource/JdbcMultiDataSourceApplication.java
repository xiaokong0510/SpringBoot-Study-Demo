package com.xiao.jdbctemplate.mutildatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author KongXiao
 * @date 2021/11/17
 */
@SpringBootApplication
public class JdbcMultiDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdbcMultiDataSourceApplication.class, args);
        System.out.println("--------service start success ------");
    }
}
