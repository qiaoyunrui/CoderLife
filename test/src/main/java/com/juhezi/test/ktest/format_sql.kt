package com.juhezi.test.ktest

import java.util.*

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */
fun main(args: Array<String>) {
    var sql = """
UPDATE daily_task
   SET id = 'id',
       title = 'title',
       content = 'content',
       create_date = 'create_date',
       limit_date = 'limit_date',
       state = 'state'
 WHERE id = 'ABCD-2134';
"""
    var newSql = sql.replace("""(\s+)""".toRegex()," ")
    println(newSql)
}