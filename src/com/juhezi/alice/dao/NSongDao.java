package com.juhezi.alice.dao;

import com.juhezi.alice.domain.NSong;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Juhezi on 2017/4/26.
 */
public interface NSongDao {

    void add(NSong song) throws SQLException;

    void delete(String userId, String songId) throws SQLException;

    List<NSong> findAll(String userid) throws SQLException;

}
