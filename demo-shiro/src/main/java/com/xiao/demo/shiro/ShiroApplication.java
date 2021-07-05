package com.xiao.demo.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author KongXiao
 * @date 2021/7/5
 */
@SpringBootApplication
public class ShiroApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShiroApplication.class, args);
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("local.server.port");
        System.out.println(String.format("\n------------service start successfully port=%s -----------", port));

    }
}
