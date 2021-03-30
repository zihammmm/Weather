package com.zihany.weather.data

import androidx.annotation.DrawableRes

data class TwentyFourHour(
    val time: String = "",
    @DrawableRes val icon: Int,
    val temperature: String
    )
