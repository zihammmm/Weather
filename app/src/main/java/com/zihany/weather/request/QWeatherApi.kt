package com.zihany.weather.request

import com.zihany.weather.data.qweather.DailyForecastData
import com.zihany.weather.data.qweather.HourlyForecastData
import com.zihany.weather.data.qweather.RTWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 和风天气API
 */
interface QWeatherApi {
    companion object {
        const val responseSuccess = 200
    }

    /**
     * 获取实时天气
     */
    @GET("weather/now")
    suspend fun getNowWeather(
        @Query("location") location: String,
        @Query("key") key: String = qWeatherKey
    ): RTWeatherData

    /**
     * 获取未来24小时天气预报
     */
    @GET("weather/24h")
    suspend fun get24hForecastWeather(
        @Query("location") location: String,
        @Query("key") key: String = qWeatherKey
    ): HourlyForecastData

    /**
     * 获取未来7天天气预报
     */
    @GET("weather/3d")
    suspend fun get7dForecastWeather(
        @Query("location") location: String,
        @Query("key") key: String = qWeatherKey
    ): DailyForecastData
}