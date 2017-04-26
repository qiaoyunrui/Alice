package com.juhezi.alice.dao.impl;

import com.juhezi.alice.dao.NSongDao;
import com.juhezi.alice.db.JDBCTemplete;
import com.juhezi.alice.domain.NSong;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juhezi on 2017/4/26.
 */
public class NSongDaoImpl implements NSongDao {

    private JDBCTemplete jdbcTemplete;

    public NSongDaoImpl() {
        jdbcTemplete = new JDBCTemplete();
    }

    @Override
    public void add(NSong song) throws SQLException {
        String sql = "REPLACE INTO al_songlist(userid, songid, songname, isdelete) VALUES (?,?,?,?)";
        jdbcTemplete.update(sql, song.getUserId(),
                song.getSongId(),
                song.getSongName(),
                song.isDelete());
    }

    @Override
    public void delete(String userId, String songId) throws SQLException {
        String sql = "UPDATE al_songlist SET isdelete = 0 WHERE userid =? AND songid =?";
        jdbcTemplete.update(sql, userId, songId);
    }

    @Override
    public List<NSong> findAll(String userid) throws SQLException {
        String sql = "SELECT" +
                " a.songid," +
                " a.songname," +
                " a.songstername," +
                " a.path" +
                "FROM al_songrepertory a, al_songlist b" +
                "WHERE" +
                " b.userid = ? AND a.songid = b.songid AND b.isdelete = '1'";
        return (List<NSong>) jdbcTemplete.query(sql, resultSet -> {
            List<NSong> list = new ArrayList<>();
            NSong song;
            while (resultSet.next()) {
                song = new NSong()
                        .setUserId(userid)
                        .setSongId(resultSet.getString(1))
                        .setSongName(resultSet.getString(2))
                        .setAuthor(resultSet.getString(3))
                        .setPath(resultSet.getString(4));
                list.add(song);
            }
            return list;
        }, userid);
    }
}
