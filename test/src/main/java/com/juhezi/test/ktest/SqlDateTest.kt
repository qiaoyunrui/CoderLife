package com.juhezi.test.ktest

import java.util.*

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/17.
 */
fun main(args: Array<String>) {
    var sDate = Date()
    println(sDate)
    val lDate = Date(sDate.year, sDate.month, sDate.date, 23, 59, 59)
    println(lDate)
}
