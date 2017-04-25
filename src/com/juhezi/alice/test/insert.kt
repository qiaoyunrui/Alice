package com.juhezi.alice.test

import com.juhezi.alice.dao.impl.SongDaoImpl
import com.juhezi.alice.domain.Song
import java.io.File
import java.util.*

/**
 * 把本地歌曲插入到数据库
 *
 * Created by Juhezi on 2017/4/25.
 */
fun main(args: Array<String>) {
    var dirpath = "C:\\CloudMusic"
    var targetDir = "E:\\IdeaProjects\\Alice\\web\\assets"
    var dir = File(dirpath)
    var dao = SongDaoImpl()
    for (file in dir.listFiles()) {
        var id = UUID.randomUUID().toString()
        var pair = getSongInfo(file.name)
        var name = pair.second
        var author = pair.first
        var path = "http://localhost:8090/assets/$id.mp3"
        file.renameTo(File("$targetDir\\$id.mp3"))     //移动文件
        var song = Song()
                .setId(id)
                .setName(name)
                .setAuthor(author)
                .setPath(path)
        dao.add(song)
    }
}

fun getSongInfo(str: String): Pair<String, String> {
    var res = str.split(" - ")
    return Pair(res[0], res[1].removeSuffix(".mp3"))
}