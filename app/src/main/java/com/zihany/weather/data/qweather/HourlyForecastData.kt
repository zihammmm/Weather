package com.zihany.weather.data.qweather

import com.zihany.weather.data.IData
import com.zihany.weather.request.WeatherRetrofitClient

/**
 * 逐小时天气预报数据
 * @link{https://dev.qweather.com/docs/api/weather/weather-hourly-forecast/}
 */
data class HourlyForecastData(
    val code: Int = 200,
    val updateTime: String = "2021-02-16T13:35+08:00",
    val fxLink: String = "http://hfx.link/2ax1",
    val hourly: List<Hourly> = arrayListOf(),
    val refer: Refer = Refer()
): IData {
    override fun isSuccessful(): Boolean {
        return code == WeatherRetrofitClient.QWEATHER_CODE_SUCCESS
    }

    override fun getStatus(): Int {
        return code
    }
}

data class Hourly(
    val fxTime: String,
    val temp: Int,
    val icon: Int,
    val text: String,
    val wind360: Int,
    val windDir: String,
    val windScale: String,
    val windSpeed: Int,
    val humidity: Int,
    val pop: Int,
    val precip: Double,
    val pressure: Int,
    val cloud: Int,
    val dew: Int
)
