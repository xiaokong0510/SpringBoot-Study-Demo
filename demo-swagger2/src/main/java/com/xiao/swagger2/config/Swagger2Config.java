package com.xiao.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Classname Swagger2Config
 * @Description Swagger2 配置
 * @Date 2021/1/22
 * @Author KongX
 * @Version: 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 构建一个DocketBean
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                .select()
                // 控制暴露出去的路径下的实例
                // 如果某个接口不想暴露,可以使用注解 @ApiIgnore
                .apis(RequestHandlerSelectors.basePackage("com.xiao.swagger2.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 构建 api文档的详细信息函数
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("Spring Boot Swagger2 构建restful API")
                // 条款地址
                .termsOfServiceUrl("https://www.kongxiao.top")
                // 创建人
                .contact(new Contact("kongxiao", "https://www.kongxiao.top", "xxx@163.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                .build();
    }
}
