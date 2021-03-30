package com.zihany.weather.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zihany.weather.data.Weather
import com.zihany.weather.mvvm.model.WeatherPageModel

class WeatherPageViewModel(
    private val model: WeatherPageModel
): ViewModel() {
    private val _weatherLiveData = MutableLiveData<Weather>()
    val weatherLiveData: LiveData<Weather> = _weatherLiveData

    private fun onWeatherChange(weather: Weather) {
        _weatherLiveData.value = weather
    }

    fun getWeather(): Weather {
        val weather = model.getWeather()
        onWeatherChange(weather)
        return weather
    }
}