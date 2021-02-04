package com.xiao.mybatis.common;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Classname ApiResponse
 * @Description 通用API接口返回
 * @Date 2021/1/26
 * @Author KongX
 * @Version: 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = -8987146499044811408L;
    /**
     * 通用返回状态
     */
    private int code;
    /**
     * 通用返回信息
     */
    private String message;
    /**
     * 通用返回数据
     */
    private Object data;

    public static  ApiResponse ok(Object data) {
        return (ApiResponse) builder().code(200).message("操作成功").data(data).build();
    }

    public static  ApiResponse ok() {
        return (ApiResponse) builder().code(200).message("操作成功").build();
    }

    public static  ApiResponse error() {
        return (ApiResponse) builder().code(500).message("操作失败").build();
    }

}
