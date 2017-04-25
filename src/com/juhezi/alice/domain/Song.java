package com.juhezi.alice.domain;

import com.juhezi.alice.util.JSONable;

/**
 * Created by Juhezi on 2017/4/25.
 */
public class Song implements JSONable {
    private String id;
    private String name;    //歌名
    private String author;  //歌手
    private String path;    //歌曲存放路径

    public Song() {
    }

    public Song(String id, String name, String author, String path) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public Song setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Song setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Song setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Song setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public String toJSON() {
        return "{" + "\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"author\":\"" + author + "\",\"path\":\"" + path + "\"}";
    }
}
