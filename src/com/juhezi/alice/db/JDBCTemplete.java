package com.juhezi.alice.db;

import com.juhezi.alice.base.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qiao1 on 2017/3/8.
 */
public class JDBCTemplete {

    public int update(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    ps.setObject(i + 1, args[i]);
                }
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    public Object query(String sql, ResultSetHandler handler, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1
                            , args[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            return handler.doHandler(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}
