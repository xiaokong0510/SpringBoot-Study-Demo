package com.xiao.response;

import lombok.Data;

/**
 * @Classname CommonResult
 * @Description 通用返回信息实体类
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@Data
public class CommonResult {

    private int code;
    private Object data;
    private String msg;

    public static CommonResult success(Object data, String msg) {
        CommonResult r = new CommonResult();
        r.setCode(200);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static CommonResult error(Object data, String msg) {
        CommonResult r = new CommonResult();
        r.setCode(-1);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
}
