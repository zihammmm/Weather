package com.zihany.weather.utils

/**
 * 将"4"转换为“星期四”
 */
fun String.toWeek(): String {
    return when (this) {
        "1" -> "星期一"
        "2" -> "星期二"
        "3" -> "星期三"
        "4" -> "星期四"
        "5" -> "星期五"
        "6" -> "星期六"
        "7" -> "星期天"
        else -> "错误日期"
    }
}