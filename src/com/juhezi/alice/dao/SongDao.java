package com.juhezi.alice.dao;

import com.juhezi.alice.domain.Song;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Juhezi on 2017/4/25.
 */
public interface SongDao {

    /**
     * 添加歌曲
     * @param song
     * @throws SQLException
     */
    void add(Song song) throws SQLException;

    /**
     * 通过歌名或者歌手名进行查询
     *
     * @param name
     * @return
     * @throws SQLException
     */
    List<Song> findByName(String name) throws SQLException;

    /**
     * 通过歌曲 id 进行查询
     *
     * @param id
     * @return
     * @throws SQLException
     */
    Song findById(String id) throws SQLException;

}
