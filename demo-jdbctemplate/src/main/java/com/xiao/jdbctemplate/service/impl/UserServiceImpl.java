package com.xiao.jdbctemplate.service.impl;

import com.xiao.jdbctemplate.entity.User;
import com.xiao.jdbctemplate.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

/**
 * @author KongXiao
 * @date 2021/10/28
 */
@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;

    UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> selectAll() {
        String sql = "SELECT * FROM `user`";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .phoneNumber(resultSet.getString("phone_number"))
                .status(resultSet.getByte("status"))
                .build());
    }

    @Override
    public User selectById(Long id) {
        String sql = "SELECT * FROM `user` WHERE `id` = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .phoneNumber(resultSet.getString("phone_number"))
                .status(resultSet.getByte("status"))
                .build(), id);
    }

    @Override
    public Long add(User model) {
        String sql = "INSERT INTO `user` (`name`,`phone_number`,`status`,`create_time`,`update_time`) values (?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
            psst.setString(1, model.getName());
            psst.setString(2, model.getPhoneNumber());
            psst.setByte(3, model.getStatus());
            psst.setObject(4, model.getCreateTime());
            psst.setObject(5, model.getUpdateTime());
            return psst;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public int update(User model) {
        String sql = "UPDATE `user` SET `name` = ?, `phone_number` = ?,`status` = ? where id =  ? ";
        return jdbcTemplate.update(sql, model.getName(), model.getPhoneNumber(), model.getStatus(), model.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql = "UPDATE `user` SET `status` = 0 where id =  ? ";
        return jdbcTemplate.update(sql, id);
    }
}
