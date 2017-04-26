package com.juhezi.alice.test;

import com.juhezi.alice.dao.SongDao;
import com.juhezi.alice.dao.UserDao;
import com.juhezi.alice.dao.impl.SongDaoImpl;
import com.juhezi.alice.dao.impl.UserDaoImpl;
import com.juhezi.alice.db.JDBCTemplete;
import com.juhezi.alice.domain.Song;
import com.juhezi.alice.domain.User;
import com.juhezi.alice.util.JSON;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianlong on 2017/3/22.
 */
public class Test {
    public static void main(String[] args) {
        /*String sql = "select songid,songname,songstername,path from al_songrepertory " +
                "where songname=? or songstername=? and isdelete = '1'";
        List<Song> songs = (List<Song>) new JDBCTemplete().query(sql, resultSet -> {
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
        }, "薛之谦", "薛之谦");*/
        SongDao dao = new SongDaoImpl();
        List<Song> songs = null;
        try {
            songs = dao.findByName("薛之谦");
            System.out.println(JSON.toJSON(new ArrayList<>(songs)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
