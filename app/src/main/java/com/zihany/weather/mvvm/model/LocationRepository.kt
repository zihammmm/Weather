package com.zihany.weather.mvvm.model

import com.zihany.network.data.RequestResult
import com.zihany.weather.data.location.LocationData

interface LocationRepository: IRepository {
    suspend fun getCityInfo(location: String): RequestResult<LocationData>
}