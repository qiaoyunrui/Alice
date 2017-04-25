package com.juhezi.alice.dao.impl;

import com.juhezi.alice.dao.SongDao;
import com.juhezi.alice.db.JDBCTemplete;
import com.juhezi.alice.domain.Song;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Juhezi on 2017/4/25.
 */
public class SongDaoImpl implements SongDao {

    private JDBCTemplete jdbcTemplete;

    public SongDaoImpl() {
        jdbcTemplete = new JDBCTemplete();
    }

    @Override
    public List<Song> findByName(String name) throws SQLException {
        // TODO: 2017/4/25  
        return null;
    }

    @Override
    public Song findById(String id) throws SQLException {
        // TODO: 2017/4/25
        return null;
    }
}
