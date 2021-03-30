package com.zihany.weather.utils

import com.zihany.weather.R

object WeatherType {

    /**
     * error
     */
    const val NA = 99

    /**
     * 晴天
     */
    const val SUNNY = 0

    /**
     * 多云
     */
    const val CLOUDY = 1

    /**
     * 阴沉
     */
    const val OVERCAST = 2

    /**
     * 阵雨
     */
    const val SHOWER = 3

    /**
     * 雷阵雨
     */
    const val THUNDER_SHOWER = 4

    /**
     * 冰雹雷雨
     */
    const val THUNDER_SHOWER_WITH_HAIL = 5

    /**
     * 雨雪
     */
    const val RAIN_SNOW = 6

    /**
     * 小雨
     */
    const val SMALL_RAIN = 7

    /**
     * 中雨
     */
    const val MID_RAIN = 8

    /**
     * 大雨
     */
    const val BIG_RAIN = 9

    /**
     * 暴雨
     */
    const val RAINSTORM = 10

    /**
     * 大暴雨
     */
    const val DOWNPOUR = 11

    /**
     * 雨
     */
    const val TORREN_RAIN = 12

    /**
     * 雪乱
     */
    const val SNOW_FLURRY = 13

    /**
     * 小雪
     */
    const val SMALL_SNOW = 14

    /**
     * 中雪
     */
    const val MID_SNOW = 15

    /**
     * 大雪
     */
    const val BIG_SNOW = 16

    /**
     * 暴风雪
     */
    const val SNOWSTORM = 17

    /**
     * 有雾
     */
    const val FOGGY = 18

    /**
     * 冰雨
     */
    const val ICE_RAIN = 19

    /**
     * 沙暴
     */
    const val SANDSTORM = 20

    /**
     * 小到中雨
     */
    const val SMALL_TO_MIDRAIN = 21

    /**
     * 中间大雨
     */
    const val MID_TO_BIGRAIN = 22

    /**
     * 大到暴风雨
     */
    const val BIG_TO_STORMRAIN = 23

    /**
     * 从暴风雨到暴风雨
     */
    const val STORM_TO_HEAVY_STORM = 24

    /**
     * 重度暴风雨
     */
    const val HEAVY_TO_SEVERE_STORM = 25

    /**
     * 轻度下雪
     */
    const val LIGHT_TO_MODERATE_SNOW = 26

    /**
     * 中度沉重的雪
     */
    const val MODERATE_TO_HEAVY_SNOW = 27

    /**
     * 暴风雪
     */
    const val HEAVY_SNOW_TO_SNOWSTORM = 28

    /**
     * 雾霾
     */
    const val DUST = 29

    /**
     * ��ɳ
     */
    const val JANSA = 30

    /**
     * 强暴风雨
     */
    const val STRONG_SANDSTORM = 31

    /**
     * 结冰
     */
    // const val ICE = 32

    /**
     * 阴霾
     */
    const val HAZE = 53
}

fun getWeatherIcon(weatherId: Int): Int {
    return when (weatherId) {
        WeatherType.SUNNY -> R.drawable.n_weather_icon_sunny
        WeatherType.CLOUDY -> R.drawable.n_weather_icon_cloud
        WeatherType.OVERCAST -> R.drawable.n_weather_icon_overcast
        WeatherType.SHOWER -> R.drawable.n_weather_icon_shower
        WeatherType.THUNDER_SHOWER -> R.drawable.n_weather_icon_thunder_rain
        WeatherType.THUNDER_SHOWER_WITH_HAIL -> R.drawable.n_weather_icon_rain_ice
        WeatherType.RAIN_SNOW -> R.drawable.n_weather_icon_rain_snow
        WeatherType.SMALL_RAIN, WeatherType.SMALL_TO_MIDRAIN -> R.drawable.n_weather_icon_small_rain
        WeatherType.MID_RAIN, WeatherType.MID_TO_BIGRAIN -> R.drawable.n_weather_icon_middle_rain
        WeatherType.BIG_RAIN, WeatherType.BIG_TO_STORMRAIN -> R.drawable.n_weather_icon_big_rain
        WeatherType.RAINSTORM, WeatherType.DOWNPOUR, WeatherType.TORREN_RAIN, WeatherType.STORM_TO_HEAVY_STORM,
        WeatherType.HEAVY_TO_SEVERE_STORM -> R.drawable.n_weather_icon_storm
        WeatherType.SNOW_FLURRY -> R.drawable.n_weather_icon_flurry
        WeatherType.SMALL_SNOW, WeatherType.LIGHT_TO_MODERATE_SNOW -> R.drawable.n_weather_icon_light_snow
        WeatherType.MID_SNOW, WeatherType.MODERATE_TO_HEAVY_SNOW -> R.drawable.n_weather_icon_moderate_snow
        WeatherType.BIG_SNOW, WeatherType.HEAVY_SNOW_TO_SNOWSTORM -> R.drawable.n_weather_icon_heavy_snow
        WeatherType.SNOWSTORM -> R.drawable.n_weather_icon_snow_storm
        WeatherType.FOGGY -> R.drawable.n_weather_icon_fog
        WeatherType.ICE_RAIN -> R.drawable.n_weather_icon_rain_ice
        WeatherType.SANDSTORM -> R.drawable.n_weather_icon_sand_storm
        WeatherType.DUST -> R.drawable.n_weather_icon_dust
        WeatherType.JANSA -> R.drawable.n_weather_icon_sand
        WeatherType.STRONG_SANDSTORM -> R.drawable.n_weather_icon_sand_storm
        WeatherType.HAZE -> R.drawable.n_weather_icon_haze
        WeatherType.NA -> R.drawable.n_weather_icon_na
        else -> R.drawable.n_weather_icon_na
    }

}