package com.zihany.weather.data

import androidx.annotation.DrawableRes
import com.zihany.weather.R

enum class WindDirection(val zhName: String) {
    NORTH("北"),
    SOUTH("南"),
    WEST("西"),
    EAST("东"),
    NORTHEAST("东北"),
    NORTHWEST("西北"),
    SOUTHEAST("东南"),
    SOUTHWEST("西南"),
    NODIRECTION("无风向"),
    UNKNOWN("旋转不定")

}

/**
 * 标准实况天气数据
 */
data class StandardCurrentWeather(
    val city: String = "北京",
    val weather: String = "晴",
    val currentTemperature: Int = 0,
    val weatherDetails: List<BasicWeather> = arrayListOf(BasicWeather()),
    @DrawableRes val background: Int = R.drawable.home_bg_1,
    @DrawableRes val backgroundGif: Int = R.drawable.bg_topgif_2,
)

/**
 * 一周标准预测天气数据
 */
data class StandardForecastWeatherList(
    val allWeatherData: List<StandardForecastWeather> = arrayListOf(StandardForecastWeather())
)

/**
 * 单日标准预测天气数据
 */
data class StandardForecastWeather(
    val date: String = "yyyy-mm-dd",
    val week: String = "星期一",
    val dayWeather: String = "晴",
    val nightWeather: String = "晴",
    val dayTemp: Int = 20,
    val nightTemp: Int = 20,
    val dayWind: String = "北",
    val nightWind: String = "北",
    val dayPower: String = "3",
    val nightPower: String = "4",
    @DrawableRes val icon: Int = R.drawable.n_weather_icon_sunny,
)


