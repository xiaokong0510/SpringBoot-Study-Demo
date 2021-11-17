package com.xiao.jpa.repository;

import com.xiao.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author KongXiao
 * @date 2021/10/29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 根据用户名和手机号查询
     *
     * @param name
     * @param phoneNumber
     * @return
     */
    User findByNameAndPhoneNumber(String name, String phoneNumber);

    /**
     * @param name
     * @return
     */
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);


}
