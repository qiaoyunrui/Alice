package com.juhezi.alice.dao.impl;

import com.juhezi.alice.dao.SongDao;
import com.juhezi.alice.db.JDBCTemplete;
import com.juhezi.alice.domain.Song;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 对曲库中所有歌曲的操作
 * Created by Juhezi on 2017/4/25.
 */
public class SongDaoImpl implements SongDao {

    private JDBCTemplete jdbcTemplete;

    public SongDaoImpl() {
        jdbcTemplete = new JDBCTemplete();
    }

    @Override
    public List<Song> findByName(String name) throws SQLException {
        String sql = "select songid,songname from al_songrepertory " +
                "where songname=? or songtername=? and isdelete = '1'";
        return (List<Song>) jdbcTemplete.query(sql, resultSet -> {
            List<Song> list = new ArrayList<>();
            Song song;
            while (resultSet.next()) {
                song = new Song()
                        .setId(resultSet.getString(1))
                        .setName(resultSet.getString(2))
                        .setAuthor(resultSet.getString(3))
                        .setPath(resultSet.getString(4));
                list.add(song);
            }
            return list;
        }, name, name);
    }

    @Override
    public Song findById(String id) throws SQLException {
        String sql = "select songid,songname,songstername,path " +
                "from al_songrepertory where id =?";
        return (Song) jdbcTemplete.query(sql, resultSet -> {
            Song song = null;
            if (resultSet.next()) {
                song = new Song()
                        .setId(resultSet.getString(1))
                        .setName(resultSet.getString(2))
                        .setAuthor(resultSet.getString(3))
                        .setPath(resultSet.getString(4));
            }
            return song;
        }, id);
    }
}
