package com.xiao.interceptor;

import com.alibaba.fastjson.JSON;
import com.xiao.anno.SignVerify;
import com.xiao.utils.HttpUtil;
import com.xiao.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * @Classname SignInterceptor
 * @Description TODO
 * @Date 2020/10/30
 * @Author KongX
 * @version: 1.0.0
 */
@Component
@Slf4j
public class SignVerifyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("------进入拦截器------");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要验签
        SignVerify methodAnnotation = method.getAnnotation(SignVerify.class);
        // 没有 @SignVerify 注解，无需验签名
        if (methodAnnotation == null) {
            return true;
        }
        //将URL的参数和body参数合并
        Map<String, String> allParams = HttpUtil.getAllParams(request);
        log.info("------获取到的请求参数为------" + allParams);
        //依次检查各变量是否存在
        if (!allParams.containsKey("sign")) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errno", -1);
            resultMap.put("errmsg", "缺少sign参数");
            returnJson(response, JSON.toJSONString(resultMap));
            log.info("------缺少sign参数------");
            return false;
        }
        if (!allParams.containsKey("timestamp")) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errno", -1);
            resultMap.put("errmsg", "缺少timestamp参数");
            returnJson(response, JSON.toJSONString(resultMap));
            log.info("------缺少timestamp参数------");
            return false;
        }
        //验签
        if (!SignUtil.verifySign(allParams)) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errno", -1);
            resultMap.put("errmsg", "sign校验不通过");
            returnJson(response, JSON.toJSONString(resultMap));
            log.info("------sign校验不通过------");
            return false;
        }
        log.info("------签名通过------");
        return true;
    }

    /**
     * 返回客户端数据
     *
     * @param response
     * @param json
     * @throws Exception
     */
    private void returnJson(HttpServletResponse response, String json) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
