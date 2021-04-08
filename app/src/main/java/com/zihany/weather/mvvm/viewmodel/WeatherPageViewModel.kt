package com.zihany.weather.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zihany.network.data.RequestResult
import com.zihany.weather.data.location.LocationData
import com.zihany.weather.data.standard.StandardCurrentWeather
import com.zihany.weather.data.standard.StandardDailyWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList
import com.zihany.weather.mvvm.model.GEOLocationRepository
import com.zihany.weather.mvvm.model.QWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherPageViewModel : ViewModel() {
    companion object {
        const val DEFAULT_LOCATION = "101010100"
        const val TAG = "WeatherPageViewModel"
    }

    val location: MutableLiveData<String> = MutableLiveData(DEFAULT_LOCATION)

    private val weatherModel = QWeatherRepository()
    private val locationModel = GEOLocationRepository()

    private val _baseWeatherLiveData = MutableLiveData(StandardCurrentWeather())
    val baseWeatherLiveData: LiveData<StandardCurrentWeather>
        get() = _baseWeatherLiveData

    private val _hourlyWeatherLiveData = MutableLiveData(StandardHourlyWeatherList())
    val hourlyWeatherLiveData: LiveData<StandardHourlyWeatherList>
        get() = _hourlyWeatherLiveData

    private val _allWeatherLiveData = MutableLiveData(StandardDailyWeatherList())
    val allWeatherLiveData: LiveData<StandardDailyWeatherList>
        get() = _allWeatherLiveData

    private val _cityInfo = MutableLiveData(LocationData())
    val cityInfo: LiveData<LocationData>
        get() = _cityInfo

    private val _errorMsg = MutableLiveData<RequestResult.Error>()
    val errorMsg: LiveData<RequestResult.Error>
        get() = _errorMsg

    fun getNowWeather(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherModel.getNowWeather(location)
            if (response is RequestResult.Success) {
                _baseWeatherLiveData.postValue(response.data)
            } else {
                _errorMsg.postValue(response as RequestResult.Error)
            }
        }
    }

    fun getHourlyWeather(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherModel.getHourlyWeather(location)
            if (response is RequestResult.Success) {
                _hourlyWeatherLiveData.postValue(response.data)
            } else {
                _errorMsg.postValue(response as RequestResult.Error)
            }
        }
    }

    fun getDailyForecastData(location: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val response = weatherModel.getDailyWeather(location)
            if (response is RequestResult.Success) {
                _allWeatherLiveData.postValue(response.data)
            } else {
                _errorMsg.postValue(response as RequestResult.Error)
            }

        }
    }

    fun getCityInfo(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = locationModel.getCityInfo(location)
            if (response is RequestResult.Success) {
                _cityInfo.postValue(response.data)
            } else {
                _errorMsg.postValue(response as RequestResult.Error)
            }

        }
    }
}