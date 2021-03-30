package com.zihany.weather.request

import com.zihany.weather.request.data.AllWeatherData
import com.zihany.weather.request.data.BaseWeatherData
import retrofit2.http.GET
import retrofit2.http.Query

enum class WeatherKind(val kind: String){
    BASE("base"),
    ALL("all")
}

/**
 * 高德天气请求接口
 */
interface ApiService {
    companion object{
        const val key = "6786784222cbe71f2406f87c7796d3d3"
    }

    /**
     * 获取当前天气
     */
    @GET
    suspend fun getBaseWeather(@Query("city") adcode: Int,
                               @Query("key") key: String = ApiService.key): BaseWeatherData

    /**
     * 获取预测天气
     */
    @GET
    suspend fun getALLWeather(@Query("city") adcode: Int,
                              @Query("key") key: String = ApiService.key): AllWeatherData
}