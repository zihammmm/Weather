package com.zihany.weather.data.qweather

/**
 * 逐小时天气预报数据
 * @link{https://dev.qweather.com/docs/api/weather/weather-hourly-forecast/}
 */
data class HourlyForecastData(
    val code: Int,
    val updateTime: String,
    val fxLink: String,
    val hourly: List<Hourly>,
    val refer: Refer
)

data class Hourly(
    val fxTime: String,
    val temp: Int,
    val icon: Int,
    val text: String,
    val wind360: Int,
    val windDir: String,
    val windScale: Int,
    val windSpeed: Int,
    val humidity: Int,
    val pop: Int,
    val precip: Double,
    val pressure: Int,
    val cloud: Int,
    val dew: Int
)
