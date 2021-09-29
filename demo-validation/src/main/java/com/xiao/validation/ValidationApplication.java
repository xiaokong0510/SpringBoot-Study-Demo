package com.xiao.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname ValidationApplication
 * @Description TODO
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
@Slf4j
public class ValidationApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(ValidationApplication.class,args);
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("\n---------------------------------------------------------\n" +
                "\tApplication is running! Access address: http://{}:{}" +
                "\n---------------------------------------------------------\n", ip, port);
    }
}
