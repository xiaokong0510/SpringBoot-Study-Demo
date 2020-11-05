package com.xiao.config;

import com.xiao.interceptor.SignVerifyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Classname SignVerifyMvcConfiguration
 * @Description TODO
 * @Date 2020/11/2
 * @Author KongX
 * @version: 1.0.0
 */
@Primary
@Configuration
public class SignVerifyMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private SignVerifyInterceptor signVerifyInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signVerifyInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
