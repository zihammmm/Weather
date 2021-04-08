package com.zihany.weather.mvvm.model

import com.zihany.network.data.RequestResult
import com.zihany.network.data.succeeded
import com.zihany.network.data.successOr
import com.zihany.weather.data.qweather.DailyForecastData
import com.zihany.weather.data.qweather.HourlyForecastData
import com.zihany.weather.data.qweather.RTWeatherData
import com.zihany.weather.data.standard.StandardCurrentWeather
import com.zihany.weather.data.standard.StandardDailyWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList
import com.zihany.weather.data.toStandardData
import com.zihany.weather.request.WeatherRetrofitClient

class QWeatherRepository : WeatherRepository {
    override suspend fun getNowWeather(location: String): RequestResult<StandardCurrentWeather> {
        val result =  execute(
            response = safeApiCall(
                call = { WeatherRetrofitClient.weatherClient.getNowWeather(location) },
                errorMessage = "getNowWeather fail"
            )
        )

        return if (result.succeeded) {
            RequestResult.Success(result.successOr(RTWeatherData()).toStandardData(location))
        }else {
            result as RequestResult.Error
        }
    }

    override suspend fun getHourlyWeather(location: String): RequestResult<StandardHourlyWeatherList> {
        val result = execute(
            response = safeApiCall(
                call = { WeatherRetrofitClient.weatherClient.get24hForecastWeather(location)},
                errorMessage = "getHourlyWeather fail"
            )
        )

        return if (result.succeeded) {
            RequestResult.Success(result.successOr(HourlyForecastData()).toStandardData())
        }else {
            result as RequestResult.Error
        }
    }

    override suspend fun getDailyWeather(location: String): RequestResult<StandardDailyWeatherList> {
        val result = execute(
            response = safeApiCall(
                call = { WeatherRetrofitClient.weatherClient.get7dForecastWeather(location)},
                errorMessage = "getDailyWeather fail"
            )
        )

        return if (result.succeeded) {
            RequestResult.Success(result.successOr(DailyForecastData()).toStandardData())
        } else {
            result as RequestResult.Error
        }
    }
}