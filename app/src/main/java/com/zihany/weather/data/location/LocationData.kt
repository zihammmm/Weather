package com.zihany.weather.data.location

import com.zihany.weather.data.qweather.Refer

data class LocationData(
    val code: Int = 200,
    val location: List<Location> = arrayListOf(Location()),
    val refer: Refer = Refer()
)

data class Location(
    val name: String = "未知",
    val id: String = "101010100",
    val lat: Double = 39.90498,
    val lon: Double = 116.40528,
    val adm2: String = "北京",
    val adm1: String = "北京市",
    val country: String = "中国",
    val tz: String = "Asia/Shanghai",
    val utfOffset: String = "+08:00",
    val isDst: Int = 0,
    val type: String = "city",
    val rank: Int = 10,
    val fxLink: String = "http://hfx.link/2ax1"
)
