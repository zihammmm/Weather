package com.zihany.weather.request

import com.zihany.weather.request.data.AllWeatherData
import com.zihany.weather.request.data.BaseWeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRequest {
    const val TAG = "ApiRequest"

    private val instance = Retrofit.Builder()
        .baseUrl("https://restapi.amap.com/v3/weather/weatherInfo")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun requestBaseWeather(city: Int): BaseWeatherData {
        return instance.getBaseWeather(city)
    }

    suspend fun requestAllWeather(city: Int): AllWeatherData {
        return instance.getALLWeather(city)
    }
}