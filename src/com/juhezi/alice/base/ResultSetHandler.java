package com.juhezi.alice.base;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qiao1 on 2017/3/8.
 */
public interface ResultSetHandler {

    Object doHandler(ResultSet resultSet) throws SQLException;

}
