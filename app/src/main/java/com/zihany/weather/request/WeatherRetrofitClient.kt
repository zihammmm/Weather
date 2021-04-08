package com.zihany.weather.request

import com.zihany.network.BaseRetrofitClient
import retrofit2.Retrofit

object WeatherRetrofitClient {
    const val QWEATHER_CODE_SUCCESS = 200
    private const val qWeatherUrl = "https://devapi.qweather.com/v7/"
    private val baseRetrofitClient = BaseRetrofitClient()

    private val weatherInstance: Retrofit by lazy {
        baseRetrofitClient.baseBuilder
            .baseUrl(qWeatherUrl)
            .build()
    }

    val weatherClient: QWeatherApi by lazy {
        weatherInstance.create(QWeatherApi::class.java)
    }
}

