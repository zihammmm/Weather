package com.zihany.weather

import android.app.Application
import com.zihany.weather.data.AppContainer
import com.zihany.weather.data.AppContainerImpl

class WeatherApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}