package com.zihany.weather.utils

import org.junit.Test

class DateKtTest{
    private val test_str = "2021-02-16T13:35+08:00"

    @Test
    fun testDateFormat() {
        val date = parseString2Date(test_str)
        println(date)
    }
}