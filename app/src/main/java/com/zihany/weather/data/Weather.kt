package com.zihany.weather.data

import androidx.annotation.DrawableRes
import com.zihany.network.data.AllWeatherData
import com.zihany.network.data.BaseWeatherData
import com.zihany.weather.R

data class Weather(
    val weather: Int = R.string.weather_sunny,
    val city: Int = R.string.city_new_york,
    val currentTemperature: Int = 0,
    val quality: Int = 0,
    @DrawableRes val background: Int = R.drawable.home_bg_1,
    @DrawableRes val backgroundGif: Int = R.drawable.bg_topgif_2,
    val twentyFourHours: List<TwentyFourHour> = arrayListOf(),
    val weekWeathers: List<WeekWeather> = arrayListOf(),
    val basicWeathers: List<BasicWeather> = arrayListOf(),
    val baseWeatherData: BaseWeatherData,
    val allWeatherData: AllWeatherData
)
