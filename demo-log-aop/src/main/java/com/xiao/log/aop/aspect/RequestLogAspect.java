package com.xiao.log.aop.aspect;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Classname LogAspect
 * @Description 日志切面类
 * @Date 2021/1/18
 * @Author KongX
 * @version: 1.0.0
 */
@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    /**
     * 定义切入点，execution表达式分为5部分
     * 1.第一个*号：表示返回类型， *号表示所有的类型
     * 2.包名
     * 3.类名，*Controller表示所有以Controller结尾的类
     * 4.方法名，*号表示所有的方法
     * 5.参数列表，两个句点表示任何参数
     */
    @Pointcut("execution(public * com.xiao.log.aop.controller.*Controller.*(..))")
    public void requestLog() {
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("requestLog()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes.getRequest());
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        RequestInfo requestInfo = RequestInfo.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                        proceedingJoinPoint.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint))
                .result(result)
                .timeCost(System.currentTimeMillis() - startTime).build();
        log.info("Request Info      : {}", JSONUtil.toJsonStr(requestInfo));
        return result;
    }

    /**
     * 切点抛出异常后执行
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "requestLog()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes.getRequest());
        RequestErrorInfo requestErrorInfo = RequestErrorInfo.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(getIp(request))
                .classMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getRequestParamsByJoinPoint(joinPoint))
                .exception(e).build();
        log.info("Error Request Info      : {}", JSONUtil.toJsonStr(requestErrorInfo));
    }

    /**
     * 获取入参
     *
     * @param proceedingJoinPoint
     * @return
     */
    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        if (ArrayUtil.isEmpty(paramNames) || ArrayUtil.isEmpty(paramValues)) {
            return Collections.emptyMap();
        }
        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();
        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            // 如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                // 获取文件名
                value = file.getOriginalFilename();
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip) || ("0:0:0:0:0:0:0:1").equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }
}
