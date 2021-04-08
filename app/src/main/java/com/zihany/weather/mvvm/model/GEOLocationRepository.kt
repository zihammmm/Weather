package com.zihany.weather.mvvm.model

import com.zihany.network.data.RequestResult
import com.zihany.network.data.succeeded
import com.zihany.network.data.successOr
import com.zihany.weather.data.location.LocationData
import com.zihany.weather.request.GeoRetrofitClient

class GEOLocationRepository: LocationRepository {
    override suspend fun getCityInfo(location: String): RequestResult<LocationData> {
        val result = execute(
            response = safeApiCall(
                call = {GeoRetrofitClient.geoClient.getCityInfo(location)},
                errorMessage = "getCityInfo fail"
            )
        )

        return if (result.succeeded) {
            RequestResult.Success(result.successOr(LocationData()))
        } else {
            result as RequestResult.Error
        }
    }
}