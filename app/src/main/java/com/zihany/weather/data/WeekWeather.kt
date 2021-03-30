package com.zihany.weather.data

import androidx.annotation.DrawableRes

data class WeekWeather(
    val weekStr: String = "",
    @DrawableRes val icon: Int,
    val temperature: String = ""
)
