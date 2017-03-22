package com.juhezi.alice.dao.impl;

import com.juhezi.alice.base.ResultSetHandler;
import com.juhezi.alice.dao.UserDao;
import com.juhezi.alice.db.JDBCTemplete;
import com.juhezi.alice.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/3/8.
 */
public class UserDaoImpl implements UserDao {

    private JDBCTemplete jdbcTemplete;

    public UserDaoImpl() {
        jdbcTemplete = new JDBCTemplete();
    }

    @Override
    public void add(User user) throws SQLException {
        String sql = "insert into user(id,username,password,pickname," +
                "avatar,salt,ip,address) values (?,?,?,?,?,?,?,?)";
        jdbcTemplete.update(sql, user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPickname(),
                user.getAvatar(),
                user.getSalt(),
                user.getIp(),
                user.getAddress());
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "update user set password=?,pickname=?," +
                "avatar=? where id=?";
        jdbcTemplete.update(sql, user.getPassword(),
                user.getPickname(), user.getAvatar(), user.getId());
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "delete from user where id=?";
        jdbcTemplete.update(sql, id);
    }

    @Override
    public User findById(String id) throws SQLException {
        String sql = "select username,password,pickname," +
                "avatar from user where id =?";
        return (User) jdbcTemplete.query(sql, resultSet -> {
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setUsername(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setPickname(resultSet.getString(3));
                user.setAvatar(resultSet.getString(4));
            }
            return user;
        }, id);
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "select id,username,password,pickname," +
                "avatar from user";
        return (List<User>) jdbcTemplete.query(sql, resultSet -> {
            List<User> users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getString(0))
                        .setUsername(resultSet.getString(1))
                        .setPassword(resultSet.getString(2))
                        .setPickname(resultSet.getString(3));
                users.add(user);
            }
            return users;
        });
    }
}
