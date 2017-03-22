package com.juhezi.alice.dao;

import com.juhezi.alice.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by qiao1 on 2017/3/8.
 */
public interface UserDao {

    public void add(User user) throws SQLException;

    public void update(User user) throws SQLException;

    public void delete(String id) throws SQLException;

    public User findById(String id) throws SQLException;

    public List<User> findAll() throws  SQLException;

}
