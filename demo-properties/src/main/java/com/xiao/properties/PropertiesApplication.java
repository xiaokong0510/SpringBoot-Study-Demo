package com.xiao.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname PropertiesApplication
 * @Description TODO
 * @Date 2020/10/26
 * @Author KongX
 * @version: 1.0.0
 */
@SpringBootApplication
@Slf4j
public class PropertiesApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(PropertiesApplication.class, args);
        Environment env = context.getBean(Environment.class);
        String port = env.getProperty("server.port");
        String ip = InetAddress.getLocalHost().getHostAddress();
        String profiles = env.getProperty("spring.profiles.active");
        log.info("\n---------------------------------------------------------\n" +
                "\tApplication is running!\n" +
                "\tAccess address: http://{}:{}\n" +
                "\tspring.profiles.active: {}" +
                "\n---------------------------------------------------------\n", ip, port,profiles);
    }
}
