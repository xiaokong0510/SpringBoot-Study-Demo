package com.xiao.mybatis.multidatasource.mapper.secondary;

import com.xiao.mybatis.multidatasource.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @author KongXiao
 * @date 2021/11/17
 */
@Mapper
public interface UserMapperSecondary {
    /**
     * 根据姓名查询
     *
     * @param name
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE name = #{name}")
    User selectByName(@Param("name") String name);

    /**
     * 新增用户信息
     *
     * @param model 用户信息
     * @return 成功 ：1； 失败 ： 0
     */
    int add(User model);

    /**
     * 删除
     *
     * @return
     */
    @Delete("DELETE FROM USER")
    int deleteAll();
}
