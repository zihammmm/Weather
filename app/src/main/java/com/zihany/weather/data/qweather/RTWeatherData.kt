package com.zihany.weather.data.qweather

import com.zihany.weather.data.IData
import com.zihany.weather.request.WeatherRetrofitClient

/**
 * 实时天气数据
 * @link{https://dev.qweather.com/docs/api/weather/weather-now/}
 */
data class RTWeatherData(
    val code: Int = 200,
    val updateTime: String = "2020-06-30T22:00+08:00",
    val fxLink: String = "http://hfx.link/2ax1",
    val now: Now = Now(),
    val refer: Refer = Refer()
): IData {
    override fun isSuccessful(): Boolean {
        return code == WeatherRetrofitClient.QWEATHER_CODE_SUCCESS
    }

    override fun getStatus(): Int {
        return code
    }
}

data class Now(
    val obsTime: String = "2020-06-30T21:40+08:00",
    val temp: Int = 24,
    val feelsLike: Int = 26,
    val icon: Int = 101,
    val text: String = "多云",
    val wind360: Int = 123,
    val windDir: String = "东南风",
    val windScale: Int = 1,
    val windSpeed: Int = 3,
    val humidity: Int = 72,
    val precip: Double = 0.0,
    val pressure: Int = 0,
    val vis: Int = 0,
    val cloud: Int = 0,
    val dew: Int = 0
)

data class Refer(
    val sources: List<String> = arrayListOf(),
    val license: List<String> = arrayListOf()
)
