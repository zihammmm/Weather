package com.zihany.network

import com.zihany.network.data.AllWeatherData
import com.zihany.network.data.BaseWeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 高德天气请求接口
 */
interface ApiService {

    /**
     * 获取当前天气
     */
    @GET("v3/weather/weatherInfo")
    fun getBaseWeather(@Query("city") adcode: Int,
                               @Query("key") key: String = BaseRetrofitClient.key
    ): Call<BaseWeatherData>

    /**
     * 获取预测天气
     */
    @GET("v3/weather/weatherInfo")
    fun getALLWeather(@Query("city") adcode: Int,
                              @Query("key") key: String = BaseRetrofitClient.key,
                      @Query("extensions") ext: String = "all"
    ): Call<AllWeatherData>

}