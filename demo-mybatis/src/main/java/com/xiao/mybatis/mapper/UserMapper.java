package com.xiao.mybatis.mapper;

import com.xiao.mybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * @Classname UserMapper
 * @Description 用户Mapper层
 * @Date 2021/1/26
 * @Author KongX
 * @Version: 1.0.0
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    @Select("SELECT * FROM user")
    List<User> selectAll();

    /**
     * 根据id查询
     * @param id 用户id
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param("id") Long id);

    /**
     * 新增用户信息
     * @param model 用户信息
     * @return 成功 ：1； 失败 ： 0
     */
    int add(User model);

    /**
     * 修改用户信息
     * @param model 用户信息
     * @return 成功 ：1； 失败 ： 0
     */
    int update(User model);


    /**
     * 删除用户
     * @param id 用户id
     * @return 成功 ：1； 失败 ： 0
     */
    @Delete("update user set status = 0 where id = #{id}")
    int deleteById(@Param("id") Long id);
}
