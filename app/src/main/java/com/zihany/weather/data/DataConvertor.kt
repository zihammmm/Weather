package com.zihany.weather.data

import com.zihany.network.data.AllWeatherData
import com.zihany.network.data.BaseWeatherData
import com.zihany.weather.R

object DataConvertor {
    fun aMapBaseData2StandardData(source: BaseWeatherData): StandardCurrentWeather {
        return if (source.count < 1) {
            throw RuntimeException()
        } else {
            val live = source.lives[0]
            val list: MutableList<BasicWeather> = arrayListOf()
            list.add(BasicWeather("风向", live.winddirection))
            list.add(BasicWeather("风力", live.windpower))
            list.add(BasicWeather("湿度", live.humidity.toString()))

            StandardCurrentWeather(
                city = live.city,
                weather = live.weather,
                currentTemperature = live.temperature,
                weatherDetails = list
            )
        }
    }

    fun aMapAllData2StandardData(source: AllWeatherData): StandardForecastWeatherList {
        return if (source.count < 1) {
            throw RuntimeException()
        } else {
            val forecast = source.forecasts[0]
            val list: MutableList<StandardForecastWeather> = arrayListOf()

            for (cast in forecast.casts) {
                list.add(
                    StandardForecastWeather(
                        date = cast.date,
                        week = cast.week,
                        dayWeather = cast.dayweather,
                        nightWeather = cast.nightweather,
                        dayTemp = cast.daytemp,
                        nightTemp = cast.nighttemp,
                        dayWind = cast.daywind,
                        nightWind = cast.nightwind,
                        dayPower = cast.daypower,
                        nightPower = cast.nightpower,
                        icon = R.drawable.n_weather_icon_sunny
                    )
                )
            }

            StandardForecastWeatherList(
                allWeatherData = list
            )
        }
    }
}