package com.zihany.weather.data.standard

import androidx.annotation.DrawableRes
import com.zihany.weather.R

/**
 * 标准实况天气数据
 */
data class StandardCurrentWeather(
    val city: String = "北京",
    val updateTime: String = "yyyy-mm-dd",
    val weather: String = "晴",
    val currentTemperature: Int = 0,
    val weatherDetails: List<BasicWeatherDetails> = arrayListOf(BasicWeatherDetails()),
    @DrawableRes val background: Int = R.drawable.home_bg_1,
    @DrawableRes val backgroundGif: Int = R.drawable.bg_topgif_2,
)

/**
 * 一周标准预测天气数据
 */
data class StandardDailyWeatherList(
    val allWeatherData: List<StandardDailyWeather> = arrayListOf(StandardDailyWeather())
)

/**
 * 单日标准预测天气数据
 */
data class StandardDailyWeather(
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

data class StandardHourlyWeatherList(
    val hourlyWeatherList: List<StandardHourlyWeather> = arrayListOf()
)

data class StandardHourlyWeather(
    val time: String = "",
    @DrawableRes val icon: Int = R.drawable.sunny,
    val temperature: String = "xxx"
)

