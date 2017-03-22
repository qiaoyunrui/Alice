package com.juhezi.alice.domain;

/**
 * Created by qiao1 on 2017/3/8.
 */
public class User {

    private String id;
    private String username;
    private String pickname;
    private String avatar;
    private String password;
    private String salt;
    private String ip;
    private String address;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPickname() {
        return pickname;
    }

    public User setPickname(String pickname) {
        this.pickname = pickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public User setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return id + " " + username + " " + password;
    }
}
