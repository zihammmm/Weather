package com.zihany.weather.mvvm.model

import com.zihany.network.data.RequestResult
import com.zihany.weather.data.standard.StandardCurrentWeather
import com.zihany.weather.data.standard.StandardDailyWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList

/**
 * 天气数据层接口
 */
interface WeatherRepository : IRepository {
    suspend fun getNowWeather(location: String): RequestResult<StandardCurrentWeather>

    suspend fun getHourlyWeather(location: String): RequestResult<StandardHourlyWeatherList>

    suspend fun getDailyWeather(location: String): RequestResult<StandardDailyWeatherList>
}