package com.juhezi.alice.test

import com.juhezi.alice.dao.UserDao
import com.juhezi.alice.dao.impl.UserDaoImpl
import com.juhezi.alice.db.DBUtils
import com.juhezi.alice.domain.User
import java.sql.SQLException
import java.util.*
import kotlin.coroutines.experimental.buildSequence

/**
 * Created by qiao1 on 2017/3/8.
 */
fun main(args: Array<String>) {
    var user = User()
    user.setId(UUID.randomUUID().toString())
            .setUsername("Juhezi")
            .setPassword("123456")
            .setPickname("乔云瑞")
    var dao = UserDaoImpl()
    dao.add(user)
}