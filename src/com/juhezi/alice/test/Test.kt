package com.juhezi.alice.test

import com.juhezi.alice.dao.UserDao
import com.juhezi.alice.dao.impl.UserDaoImpl
import com.juhezi.alice.db.DBUtils
import com.juhezi.alice.domain.Song
import com.juhezi.alice.domain.User
import com.juhezi.alice.util.JSON
import java.sql.SQLException
import java.util.*
import kotlin.coroutines.experimental.buildSequence

/**
 * Created by qiao1 on 2017/3/8.
 */
fun main(args: Array<String>) {
    var song = Song(
            "ABC2-Adad",
            "刚好遇见你",
            "李玉刚",
            "www.baidu.com"
    )
    var list = List(10, { song })
    println(JSON.toJSON(list))
}