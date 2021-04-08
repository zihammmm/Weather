package com.zihany.weather.request

import com.zihany.network.BaseRetrofitClient
import retrofit2.Retrofit

object GeoRetrofitClient {
    const val GEO_CODE_SUCCESS = 200
    private const val geoUrl = "https://geoapi.qweather.com/v2/"
    private val baseRetrofitClient = BaseRetrofitClient()

    private val geoInstance: Retrofit by lazy {
        baseRetrofitClient.baseBuilder
            .baseUrl(geoUrl)
            .build()
    }

    val geoClient: GEOApi by lazy {
        geoInstance.create(GEOApi::class.java)
    }
}
