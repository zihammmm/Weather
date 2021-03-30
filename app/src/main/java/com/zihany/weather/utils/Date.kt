package com.zihany.weather.utils

import android.content.Context
import android.text.format.DateUtils
import java.util.*

val isZhLanguage: Boolean
    get() = Locale.getDefault().language == "zh"

val zhArray = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
val enArray = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

fun getWeekListString(week: Int): List<String> {
    val list = arrayListOf<String>()
    if (isZhLanguage) {
        for (index in week..7) {
            list.add(zhArray[index-1])
        }
    }else {
        for (index in week..7) {
            list.add(enArray[index-1])
        }
    }
    return list
}

fun Context.getDefaultDate(millis: Long): String {
    val flags = (
            DateUtils.FORMAT_SHOW_DATE
                    or DateUtils.FORMAT_ABBREV_MONTH
                    or DateUtils.FORMAT_ABBREV_WEEKDAY
            )
    var dateString: String
    var week: String
    synchronized(TimeZone::class.java) {
        dateString = DateUtils.formatDateTime(this, millis, flags)
        week = DateUtils.formatDateTime(
            this,
            millis,
            DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_ABBREV_WEEKDAY
        )
        TimeZone.setDefault(null)
    }
    return if (isZhLanguage) {
        "$dateString $week"
    } else {
        "$dateString, $week"
    }
}