package com.zihany.weather.utils

import com.zihany.weather.R

fun Int.getQWeatherIcon(): Int {
    return when (this) {
        100 -> R.drawable.sunny
        101 -> R.drawable.cloudy
        102 -> R.drawable.few_clouds
        103 -> R.drawable.partly_cloudy
        104 -> R.drawable.overcast
        150 -> R.drawable.clear
        153 -> R.drawable.partly_cloudy_night
        154 -> R.drawable.overcast
        300 -> R.drawable.shower_rain
        301 -> R.drawable.heavy_shower_rain
        302 -> R.drawable.thundershower
        303 -> R.drawable.heavy_thunderstorm
        304 -> R.drawable.thundershower_with_hail
        305 -> R.drawable.light_rain
        306 -> R.drawable.moderate_rain
        307 -> R.drawable.heavy_rain
        308 -> R.drawable.extreme_rain
        309 -> R.drawable.drizzle_rain
        310 -> R.drawable.storm
        311 -> R.drawable.heavy_storm
        312 -> R.drawable.severe_storm
        313 -> R.drawable.freezing_rain
        314 -> R.drawable.light_to_moderate_rain
        315 -> R.drawable.moderate_to_heavy_rain
        316 -> R.drawable.heavy_rain_to_storm
        317 -> R.drawable.storm_to_heavy_storm
        318 -> R.drawable.heavy_to_severe_storm
        399 -> R.drawable.rain
        350 -> R.drawable.shower_rain_night
        351 -> R.drawable.heavy_shower_rain_night
        400 -> R.drawable.light_snow
        401 -> R.drawable.moderate_snow
        402 ->R.drawable.heavy_snow
        403->R.drawable.snowstorm
        404->R.drawable.sleet
        405->R.drawable.rain_and_snow
        406->R.drawable.shower_snow
        407->R.drawable.snow_flurry
        408->R.drawable.light_to_moderate_snow
        409->R.drawable.moderate_to_heavy_snow
        410->R.drawable.heavy_snow_to_snowstorm
        499->R.drawable.snow
        456->R.drawable.shower_snow_night
        457->R.drawable.snow_flurry_night
        500->R.drawable.mist
        501->R.drawable.foggy
        502->R.drawable.haze
        503->R.drawable.sand
        504->R.drawable.dust
        507->R.drawable.duststorm
        508->R.drawable.sandstorm
        509->R.drawable.dense_fog
        510->R.drawable.strong_fog
        511->R.drawable.moderate_haze
        512->R.drawable.heavy_haze
        513->R.drawable.severe_haze
        514->R.drawable.heavy_fog
        515->R.drawable.extra_heavy_fog
        900->R.drawable.hot
        901->R.drawable.cold
        else -> R.drawable.unknown
    }
}
