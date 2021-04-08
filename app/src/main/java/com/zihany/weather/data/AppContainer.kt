package com.zihany.weather.data

import android.content.Context
import com.zihany.weather.mvvm.model.GEOLocationRepository
import com.zihany.weather.mvvm.model.LocationRepository
import com.zihany.weather.mvvm.model.QWeatherRepository
import com.zihany.weather.mvvm.model.WeatherRepository

interface AppContainer {
    val weatherRepository: WeatherRepository
    val locationRepository: LocationRepository
}

class AppContainerImpl(
    private val applicationContext: Context
): AppContainer {
    override val weatherRepository: WeatherRepository by lazy {
        QWeatherRepository()
    }
    override val locationRepository: LocationRepository by lazy {
        GEOLocationRepository()
    }
}