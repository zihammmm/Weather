package com.zihany.weather.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zihany.weather.data.location.LocationData
import com.zihany.weather.data.qweather.DailyForecastData
import com.zihany.weather.data.qweather.HourlyForecastData
import com.zihany.weather.data.qweather.RTWeatherData
import com.zihany.weather.data.standard.StandardCurrentWeather
import com.zihany.weather.data.standard.StandardDailyWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList
import com.zihany.weather.data.toStandardData
import com.zihany.weather.request.GeoRetrofitClient
import com.zihany.weather.request.WeatherRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPageViewModel : ViewModel() {
    companion object {
        const val DEFAULT_LOCATION = "101010100"
        const val TAG = "WeatherPageViewModel"
    }

    val location: MutableLiveData<String> = MutableLiveData(DEFAULT_LOCATION)

    private val _baseWeatherLiveData = MutableLiveData(StandardCurrentWeather())
    val baseWeatherLiveData: LiveData<StandardCurrentWeather>
        get() = _baseWeatherLiveData

    private val _hourlyWeatherLiveData = MutableLiveData<StandardHourlyWeatherList>()
    val hourlyWeatherLiveData: LiveData<StandardHourlyWeatherList>
        get() = _hourlyWeatherLiveData

    private val _allWeatherLiveData = MutableLiveData(StandardDailyWeatherList())
    val allWeatherLiveData: LiveData<StandardDailyWeatherList>
        get() = _allWeatherLiveData

    private val _cityInfo = MutableLiveData(LocationData())
    val cityInfo: LiveData<LocationData>
        get() = _cityInfo

    fun getNowWeather(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = WeatherRetrofitClient.weatherClient.getNowWeather(location)
                _baseWeatherLiveData.postValue(response.toStandardData(location))
            }
        }
    }

    fun getHourlyWeather(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = WeatherRetrofitClient.weatherClient.get24hForecastWeather(location)
                _hourlyWeatherLiveData.postValue(response.toStandardData())
            }
        }
    }

    fun getDailyForecastData(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = WeatherRetrofitClient.weatherClient.get7dForecastWeather(location)
                _allWeatherLiveData.postValue(response.toStandardData())
            }
        }
    }

    fun getCityInfo(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _cityInfo.postValue(GeoRetrofitClient.geoClient.getCityInfo(location))
            }
        }
    }
}