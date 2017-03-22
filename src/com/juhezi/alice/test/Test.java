package com.juhezi.alice.test;

import com.juhezi.alice.dao.UserDao;
import com.juhezi.alice.dao.impl.UserDaoImpl;
import com.juhezi.alice.domain.User;

import java.sql.SQLException;

/**
 * Created by tianlong on 2017/3/22.
 */
public class Test{
    public static void main(String[] args) {
        User user = new User();
        user.setId("hello");
        user.setUsername("skyloong");
        user.setPassword("1234");
        UserDao use = new UserDaoImpl();
        try{
            use.add(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
