package com.zihany.weather.data

import com.zihany.weather.data.qweather.DailyForecastData
import com.zihany.weather.data.qweather.HourlyForecastData
import com.zihany.weather.data.qweather.RTWeatherData
import com.zihany.weather.data.standard.*
import com.zihany.weather.request.QWeatherApi
import com.zihany.weather.utils.date2Week
import com.zihany.weather.utils.getQWeatherIcon
import com.zihany.weather.utils.parseString2Date

fun RTWeatherData.toStandardData(city: String): StandardCurrentWeather {

    return if (code == QWeatherApi.responseSuccess) {
        val list: MutableList<BasicWeatherDetails> = arrayListOf()
        list.add(BasicWeatherDetails("风向", now.windDir))
        list.add(BasicWeatherDetails("风力等级", now.windScale.toString()))
        list.add(BasicWeatherDetails("风速", now.windSpeed.toString()))
        list.add(BasicWeatherDetails("相对湿度", now.humidity.toString()))
        list.add(BasicWeatherDetails("大气压强", now.pressure.toString()))
        list.add(BasicWeatherDetails("能见度", now.vis.toString()))
        list.add(BasicWeatherDetails("露点温度", now.dew.toString()))
        list.add(BasicWeatherDetails("数据观测时间", now.obsTime))
        if (now.precip > 0.0) {
            list.add(BasicWeatherDetails("当前小时累计降水量", now.precip.toString()))
        }
        StandardCurrentWeather(
            city = city,
            updateTime = updateTime,
            weather = now.text,
            currentTemperature = now.temp
        )
    } else {
        StandardCurrentWeather()
    }
}

fun DailyForecastData.toStandardData(): StandardForecastWeatherList {
    val list: MutableList<StandardForecastWeather> = arrayListOf()
    if (code == QWeatherApi.responseSuccess) {
        for (d in daily) {
            list.add(
                StandardForecastWeather(
                    date = d.fxDate,
                    week = date2Week(d.fxDate),
                    icon = d.iconDay.getQWeatherIcon(),
                    dayTemp = d.tempMax,
                    nightTemp = d.tempMin,
                    dayWind = d.windDirDay,
                    dayPower = d.windScaleDay,
                    nightWind = d.windDirNight,
                    nightPower = d.windScaleNight,
                    dayWeather = d.textDay,
                    nightWeather = d.textNight,
                )
            )
        }
    }

    return StandardForecastWeatherList(allWeatherData = list)
}

fun HourlyForecastData.toStandardData(): StandardHourlyWeatherList {
    val list: MutableList<StandardHourlyWeather> = arrayListOf()
    if (code == QWeatherApi.responseSuccess) {
        for (h in hourly) {
            val item = StandardHourlyWeather(
                time = "${parseString2Date(h.fxTime).hours}:00",
                icon = h.icon.getQWeatherIcon(),
                temperature = h.temp.toString()
            )
            list.add(item)
        }
    }
    return StandardHourlyWeatherList(hourlyWeatherList = list)
}