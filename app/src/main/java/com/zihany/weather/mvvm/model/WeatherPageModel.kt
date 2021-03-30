package com.zihany.weather.mvvm.model

import android.icu.util.Calendar
import com.zihany.weather.R
import com.zihany.weather.data.*
import com.zihany.weather.utils.getWeatherIcon
import com.zihany.weather.utils.getWeekListString
import kotlin.random.Random

class WeatherPageModel {

    fun getWeather(): Weather {
        val random = Random
        val city = R.string.city_new_york
        val weatherEnums = WeatherEnum.values()
        val weatherEnum = weatherEnums[random.nextInt(14)]
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR)
        val twentyFourHours = arrayListOf<TwentyFourHour>()
        val weekWeathers = arrayListOf<WeekWeather>()

        for (index in hours + 1..24) {
            twentyFourHours.add(
                TwentyFourHour(
                    time = "$index:00",
                    weatherEnum.icon,
                    "${random.nextInt(29)}"
                )
            )
        }

        val week = calendar.get(Calendar.DAY_OF_WEEK)
        val weekListString = getWeekListString(week)
        for (index in weekListString.indices) {
            val small = random.nextInt(10)
            weekWeathers.add(
                WeekWeather(
                    weekListString[index],
                    getWeatherIcon(random.nextInt(35)),
                    "$small°/${small+7}°"
                )
            )
        }

        val basicWeathers = arrayListOf<BasicWeather>()
        basicWeathers.add(BasicWeather(R.string.basic_rain, "${random.nextInt(100)}%"))
        basicWeathers.add(BasicWeather(R.string.basic_humidity, "${random.nextInt(100)}%"))
        basicWeathers.add(BasicWeather(R.string.basic_body_temperature, "${random.nextInt(20)}"))
        basicWeathers.add(BasicWeather(R.string.basic_precipitation, "${random.nextInt(50)} mm"))
        basicWeathers.add(BasicWeather(R.string.basic_air_pressure, "${random.nextInt(2000)} Hpa"))
        basicWeathers.add(BasicWeather(R.string.basic_visibility, "${random.nextInt(30)} Km"))

        return Weather(
            weatherEnum.weather,
            city = city,
            currentTemperature = random.nextInt(30),
            quality = random.nextInt(100),
            background = weatherEnum.background,
            backgroundGif = weatherEnum.backgroundGif,
            twentyFourHours = twentyFourHours,
            weekWeathers = weekWeathers,
            basicWeathers = basicWeathers
        )

    }
}