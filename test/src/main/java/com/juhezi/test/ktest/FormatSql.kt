package com.juhezi.test.ktest

import java.util.*

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/22.
 */
fun main(args: Array<String>) {
    var sql = """
SELECT id,
       content,
       create_date,
       limit_date,
       state
  FROM daily_task;
"""
    var newSql = sql.replace("""(\s+)""".toRegex()," ")
    println(newSql)
}