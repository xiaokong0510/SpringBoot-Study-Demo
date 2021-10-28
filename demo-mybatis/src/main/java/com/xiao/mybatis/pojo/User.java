package com.xiao.mybatis.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Classname User
 * @Description 用户实体类
 * @Date 2021/1/25
 * @Author KongX
 * @Version: 1.0.0
 */
@Data
@Builder
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 状态，1 有效；0 无效
     */
    private Byte status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
