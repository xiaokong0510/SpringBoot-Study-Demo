package com.xiao.jpa.multidatasource.repository.secondary;

import com.xiao.jpa.multidatasource.entity.secondary.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author KongXiao
 * @date 2021/11/19
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
