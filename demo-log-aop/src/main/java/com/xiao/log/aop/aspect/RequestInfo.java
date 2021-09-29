package com.xiao.log.aop.aspect;

import lombok.Builder;
import lombok.Data;

/**
 * @author KongXiao
 * @date 2021/9/29
 */
@Data
@Builder
public class RequestInfo {

    // 线程id
    private String threadId;
    // 线程名称
    private String threadName;
    // ip
    private String ip;
    // url
    private String url;
    // http方法 GET POST PUT DELETE PATCH
    private String httpMethod;
    // 类方法
    private String classMethod;
    // 请求参数
    private Object requestParams;
    // 返回参数
    private Object result;
    // 接口耗时
    private Long timeCost;

}
