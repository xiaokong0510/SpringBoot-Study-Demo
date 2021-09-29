package com.xiao.log.aop;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @Classname LogAopApplication
 * @Description TODO
 * @Date 2021/1/18
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
@Slf4j
public class LogAopApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(LogAopApplication.class, args);
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("\n---------------------------------------------------------\n" +
                "\tApplication is running! Access address: http://{}:{}" +
                "\n---------------------------------------------------------\n", ip, port);
    }
}
