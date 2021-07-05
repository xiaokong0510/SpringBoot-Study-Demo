package com.xiao.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @Classname MapUtil
 * @Description TODO
 * @Date 2020/11/2
 * @Author KongX
 * @version: 1.0.0
 */
@Slf4j
public class MapUtil {
    /**
     * 将对象转换成Map<String, String>格式
     *
     * @param obj
     * @return
     */
    public static HashMap<String, String> getNamValMap(Object obj) {
        HashMap<String, String> map = new HashMap<>();
        Field[] fieldArr = obj.getClass().getDeclaredFields();
        try {
            for (Field field : fieldArr) {
                field.setAccessible(true);
                if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                    map.put(field.getName(), field.get(obj).toString());
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return map;
    }
}
