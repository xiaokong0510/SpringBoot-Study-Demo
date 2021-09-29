package com.xiao.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname com.xiao.HelloWorldApplication
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
@Slf4j
public class HelloWorldApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(HelloWorldApplication.class, args);
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        log.info("\n---------------------------------------------------------\n" +
                "\tApplication is running! Access address: http://{}:{}" +
                "\n---------------------------------------------------------\n", ip, port);
    }
}
