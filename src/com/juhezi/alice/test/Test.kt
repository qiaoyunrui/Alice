package com.juhezi.alice.test

import com.juhezi.alice.dao.UserDao
import com.juhezi.alice.dao.impl.UserDaoImpl
import com.juhezi.alice.db.DBUtils
import com.juhezi.alice.domain.User
import java.sql.SQLException
import java.util.*

/**
 * Created by qiao1 on 2017/3/8.
 */
fun main(args: Array<String>) {
    var dao: UserDao = UserDaoImpl()
    try {
        var user = User()
        user.id = UUID.randomUUID().toString()
        user.username = "Juhezi"
        println(user.id)
        user.password = "hello"
        dao.add(user)
    } catch (e: SQLException) {
        e.printStackTrace()
    }

}