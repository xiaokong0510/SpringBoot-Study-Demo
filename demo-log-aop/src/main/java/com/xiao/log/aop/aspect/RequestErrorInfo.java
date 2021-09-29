package com.xiao.log.aop.aspect;

import lombok.Builder;
import lombok.Data;

/**
 * @author KongXiao
 * @date 2021/9/29
 */
@Data
@Builder
public class RequestErrorInfo {
    // 线程id
    private String threadId;
    // 线程名称
    private String threadName;
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private RuntimeException exception;
}
