package com.juhezi.alice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 数据库工具类
 * <p>
 * Created by qiao1 on 2017/3/8.
 */
public class DBUtils {

    private static final String PROPERTIES_FILE_NAME = "com.juhezi.alice.db.dbconfig";
    private static final String URL_KEY = "jdbc.url";
    private static final String USERNAME_KEY = "jdbc.username";
    private static final String PASSWORD_KEY = "jdbc.password";
    private static final String DRIVER_KEY = "jdbc.driver";

    private static String URL = "";
    private static String USERNAME = "";
    private static String PASSWD = "";
    private static String DRIVER = "";

    static {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME, Locale.ENGLISH);
            URL = resourceBundle.getString(URL_KEY);
            USERNAME = resourceBundle.getString(USERNAME_KEY);
            PASSWD = resourceBundle.getString(PASSWORD_KEY);
            DRIVER = resourceBundle.getString(DRIVER_KEY);
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(ResultSet resultSet, Statement statement,
                             Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
