package com.zihany.weather.data.qweather

import com.zihany.weather.data.IData
import com.zihany.weather.request.WeatherRetrofitClient

data class DailyForecastData(
    val code: Int = 200,
    val updateTime: String = "2021-02-16T13:35+08:00",
    val fxLink: String = "http://hfx.link/2ax1",
    val daily: List<Daily> = arrayListOf(),
    val refer: Refer = Refer()
): IData {
    override fun isSuccessful(): Boolean {
        return code == WeatherRetrofitClient.QWEATHER_CODE_SUCCESS
    }

    override fun getStatus(): Int {
        return code
    }
}

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
