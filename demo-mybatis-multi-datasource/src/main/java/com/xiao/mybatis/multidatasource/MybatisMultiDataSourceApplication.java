package com.xiao.mybatis.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author KongXiao
 * @date 2021/11/17
 */
@SpringBootApplication
public class MybatisMultiDataSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisMultiDataSourceApplication.class);
        System.out.println("--------service start success ------");
    }
}
