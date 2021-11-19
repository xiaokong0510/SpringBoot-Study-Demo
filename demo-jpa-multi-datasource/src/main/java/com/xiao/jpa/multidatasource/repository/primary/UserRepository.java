package com.xiao.jpa.multidatasource.repository.primary;

import com.xiao.jpa.multidatasource.entity.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author KongXiao
 * @date 2021/10/29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
