package com.xiao.exception;

import com.xiao.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Classname GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@Component
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    /**
     * 处理请求参数缺失异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e){
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(),request.getMethod(), request.getRequestURI(),request.getQueryString());
        return CommonResult.error(e.getMessage());
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return CommonResult.error(message);
    }

    /**
     * 处理类型转换异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public CommonResult mismatchErrorHandler(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(),request.getMethod(), request.getRequestURI(),request.getQueryString());
        return CommonResult.error( e.getMessage());
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return CommonResult.error(message);
    }


}
