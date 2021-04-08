package com.zihany.weather.request

import com.zihany.weather.data.location.LocationData
import retrofit2.http.GET
import retrofit2.http.Query

interface GEOApi {
    /**
     * 根据LocationID查询城市信息
     */
    @GET("city/lookup")
    suspend fun getCityInfo(
        @Query("location") location: String,
        @Query("key") key: String = QWEATHER_KEY
    ): LocationData
}