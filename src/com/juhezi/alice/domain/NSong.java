package com.juhezi.alice.domain;

import com.juhezi.alice.util.JSONable;

/**
 * Created by Juhezi on 2017/4/26.
 */
public class NSong implements JSONable {

    private String userId;
    private String songId;
    private String songName;
    private String author;
    private String path;
    private boolean isDelete;

    public NSong() {
    }

    public NSong(String userId, String songId, String songName, String author, String path, boolean isDelete) {
        this.userId = userId;
        this.songId = songId;
        this.songName = songName;
        this.author = author;
        this.path = path;
        this.isDelete = isDelete;
    }

    public String getUserId() {
        return userId;
    }

    public NSong setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSongId() {
        return songId;
    }

    public NSong setSongId(String songId) {
        this.songId = songId;
        return this;
    }

    public String getSongName() {
        return songName;
    }

    public NSong setSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public NSong setDelete(boolean delete) {
        isDelete = delete;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public NSong setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPath() {
        return path;
    }

    public NSong setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toJSON() {
        return "{" + "\"userId\":\"" + userId + "\",\"songId\":\"" + songId + "\",\"songName\":\"" +
                songName + "\",\"author\":\"" + author + "\",\"path\":\"" + path + "\"}";
    }
}
