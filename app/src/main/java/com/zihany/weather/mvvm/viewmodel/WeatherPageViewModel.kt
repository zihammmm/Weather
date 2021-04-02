package com.zihany.weather.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zihany.network.BaseRetrofitClient
import com.zihany.network.data.AllWeatherData
import com.zihany.network.data.BaseWeatherData
import com.zihany.weather.data.DataConvertor
import com.zihany.weather.data.StandardCurrentWeather
import com.zihany.weather.data.StandardForecastWeatherList
import com.zihany.weather.mvvm.model.WeatherPageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPageViewModel(): ViewModel() {
    companion object {
        const val DEFAULT_ADCODE = 110100
        const val TAG = "WeatherPageViewModel"
    }
    private val model: WeatherPageModel = WeatherPageModel()

    private val _baseWeatherLiveData = MutableLiveData(StandardCurrentWeather())
    val baseWeatherLiveData: LiveData<StandardCurrentWeather>
        get() = _baseWeatherLiveData

    private val _allWeatherLiveData = MutableLiveData(StandardForecastWeatherList())
    val allWeatherLiveData: LiveData<StandardForecastWeatherList>
        get() = _allWeatherLiveData

    private val _cityLiveData = MutableLiveData(DEFAULT_ADCODE)
    val cityLiveData: LiveData<Int>
        get() = _cityLiveData

    fun getBaseWeather(city: Int = DEFAULT_ADCODE) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = BaseRetrofitClient.weatherClient.getBaseWeather(city)
                response.enqueue(object : Callback<BaseWeatherData> {
                    override fun onResponse(
                        call: Call<BaseWeatherData>,
                        response: Response<BaseWeatherData>
                    ) {
                        if (response.isSuccessful) {
                            _baseWeatherLiveData.value = DataConvertor.aMapBaseData2StandardData(response.body()!!)
                            Log.i(TAG, "base weather:${response.body()}")
                        }else {
                            Log.i(TAG, "get base weather fail")
                        }
                    }

                    override fun onFailure(call: Call<BaseWeatherData>, t: Throwable) {
                        Log.i(TAG, "getBaseWeather onFailure:$t")
                    }

                })
            }
        }
    }

    fun getAllWeather(city: Int = DEFAULT_ADCODE) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = BaseRetrofitClient.weatherClient.getALLWeather(city)
                response.enqueue(object : Callback<AllWeatherData> {
                    override fun onFailure(call: Call<AllWeatherData>, t: Throwable) {
                        Log.i(TAG, "getAllWeather onFailure:$t")
                    }

                    override fun onResponse(
                        call: Call<AllWeatherData>,
                        response: Response<AllWeatherData>
                    ) {
                        if (response.isSuccessful) {
                            Log.i(TAG, "all weather:${response.body()}")
                            _allWeatherLiveData.value = DataConvertor.aMapAllData2StandardData(response.body()!!)
                        }else {
                            Log.i(TAG, "get all weather fail")
                        }
                    }

                })
            }
        }
    }
}