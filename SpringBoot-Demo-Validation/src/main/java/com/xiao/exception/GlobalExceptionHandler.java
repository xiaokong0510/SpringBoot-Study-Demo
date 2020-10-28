package com.xiao.exception;

import com.xiao.response.CommonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.util.Objects;

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
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResult exceptionHandler(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        return CommonResult.error(null,Objects.requireNonNull(bindingResult.getFieldError()).getField()+Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }
}
