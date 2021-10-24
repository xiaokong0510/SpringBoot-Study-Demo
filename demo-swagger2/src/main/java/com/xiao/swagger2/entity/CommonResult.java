package com.xiao.swagger2.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname CommonResult
 * @Description 通用返回信息实体类
 * @Date 2020/10/28
 * @Author KongX
 * @version: 1.0.0
 */
@Data
@ApiModel(value = "CommonResult",description = "统一返回结果")
public class CommonResult<T> {
    @ApiModelProperty(value = "成功标识；true：成功；false:失败")
    private boolean success;
    @ApiModelProperty(value = "返回状态码；200:成功")
    private int code;
    @ApiModelProperty(value = "描述信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> r = new CommonResult<>();
        r.setCode(200);
        r.setData(data);
        return r;
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult<T> r = new CommonResult<>();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }
}
