package com.xiao.exception;

import com.xiao.response.CommonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

/**
 * @Classname GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * 参数异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e){
        return CommonResult.error(null,e.getMessage());
    }
}
