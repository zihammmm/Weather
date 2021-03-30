package com.zihany.weather.request.data

/**
 * 高德地图返回的天气数据
 */
data class BaseWeatherData(
    val status: Int,
    val count: Int,
    val info: Int,
    val infocode: Int,
    val lives: Lives,
)

data class AllWeatherData(
    val status: Int,
    val count: Int,
    val info: Int,
    val infocode: Int,
    val forecast: Forecast
)

data class Lives(
    val province: String,
    val city: String,
    val adcode: Int,
    val weather: String,
    val temperature: Int,
    val winddirection: String,
    val windpower: String,
    val humidity: Int,
    val reporttime: String
)

data class Forecast(
    val city: String,
    val adcode: Int,
    val province: String,
    val reporttime: String,
    val casts: List<Cast>
)

data class Cast(
    val date: String,
    val week: String,
    val dayweather: String,
    val nightweather: String,
    val daytemp: Int,
    val nighttemp: Int,
    val daydind: String,
    val nightwind: String,
    val daypower: String,
    val nightpower: String
)