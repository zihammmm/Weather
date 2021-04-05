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
import com.zihany.weather.data.standard.StandardForecastWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList
import com.zihany.weather.data.toStandardData
import com.zihany.weather.mvvm.model.WeatherPageModel
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

    private val model: WeatherPageModel = WeatherPageModel()
    val location: MutableLiveData<String> = MutableLiveData(DEFAULT_LOCATION)

    private val _baseWeatherLiveData = MutableLiveData(StandardCurrentWeather())
    val baseWeatherLiveData: LiveData<StandardCurrentWeather>
        get() = _baseWeatherLiveData

    private val _hourlyWeatherLiveData = MutableLiveData<StandardHourlyWeatherList>()
    val hourlyWeatherLiveData: LiveData<StandardHourlyWeatherList>
        get() = _hourlyWeatherLiveData

    private val _allWeatherLiveData = MutableLiveData(StandardForecastWeatherList())
    val allWeatherLiveData: LiveData<StandardForecastWeatherList>
        get() = _allWeatherLiveData

    private val _cityInfo = MutableLiveData(LocationData())
    val cityInfo: LiveData<LocationData>
        get() = _cityInfo

    fun getNowWeather(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = WeatherRetrofitClient.weatherClient.getNowWeather(location)
                response.enqueue(object : Callback<RTWeatherData> {
                    override fun onResponse(
                        call: Call<RTWeatherData>,
                        response: Response<RTWeatherData>
                    ) {
                        if (response.isSuccessful) {
                            _baseWeatherLiveData.value = response.body()?.toStandardData(location)
                        } else {
                            Log.d(TAG, "getNowWeather fail")
                        }
                    }

                    override fun onFailure(call: Call<RTWeatherData>, t: Throwable) {
                        Log.d(TAG, "getNowWeather onFailure")
                    }
                })
            }
        }
    }

    fun getHourlyWeather(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = WeatherRetrofitClient.weatherClient.get24hForecastWeather(location)
                response.enqueue(object : Callback<HourlyForecastData> {
                    override fun onResponse(
                        call: Call<HourlyForecastData>,
                        response: Response<HourlyForecastData>
                    ) {
                        if (response.isSuccessful) {
                            _hourlyWeatherLiveData.value = response.body()?.toStandardData()
                        } else {
                            Log.d(TAG, "getHourlyWeather fail")
                        }
                    }

                    override fun onFailure(call: Call<HourlyForecastData>, t: Throwable) {
                        Log.d(TAG, "getHourlyWeather onFailure")
                    }

                })
            }
        }
    }

    fun getDailyForecastData(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = WeatherRetrofitClient.weatherClient.get7dForecastWeather(location)
                response.enqueue(object : Callback<DailyForecastData> {
                    override fun onResponse(
                        call: Call<DailyForecastData>,
                        response: Response<DailyForecastData>
                    ) {
                        if (response.isSuccessful) {
                            _allWeatherLiveData.value = response.body()?.toStandardData()
                        } else {
                            Log.d(TAG, "getDailyWeather fail")
                        }
                    }

                    override fun onFailure(call: Call<DailyForecastData>, t: Throwable) {
                        Log.d(TAG, "getDailyWeather onFailure")
                    }

                })
            }
        }
    }

    fun getCityInfo(location: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = GeoRetrofitClient.geoClient.getCityInfo(location)
                response.enqueue(object : Callback<LocationData> {
                    override fun onResponse(
                        call: Call<LocationData>,
                        response: Response<LocationData>
                    ) {
                        if (response.isSuccessful) {
                            _cityInfo.value = response.body()
                        }else {
                            Log.d(TAG, "get city info fail")
                        }
                    }

                    override fun onFailure(call: Call<LocationData>, t: Throwable) {
                        Log.d(TAG, "getCityInfo onFailure")
                    }

                })
            }
        }
    }
}