package com.zihany.weather.data.qweather

data class DailyForecastData(
    val code: Int,
    val updateTime: String,
    val fxLink: String,
    val daily: List<Daily>,
    val refer: Refer
)

data class Daily(
    val fxDate: String,
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moonPhase: String,
    val tempMax: Int,
    val tempMin: Int,
    val iconDay: Int,
    val textDay: String,
    val iconNight: Int,
    val textNight: String,
    val wind360Day: Int,
    val windDirDay: String,
    val windScaleDay: String,
    val windSpeedDay: Int,
    val wind360Night: Int,
    val windDirNight: String,
    val windScaleNight: String,
    val windSpeedNight: Int,
    val humidity: Int,
    val precip: Double,
    val pressure: Int,
    val vis: Int,
    val cloud: Int,
    val uvIndex: Int
)
