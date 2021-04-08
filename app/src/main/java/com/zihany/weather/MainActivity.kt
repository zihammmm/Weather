package com.zihany.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.zihany.weather.data.location.LocationData
import com.zihany.weather.data.standard.StandardCurrentWeather
import com.zihany.weather.data.standard.StandardDailyWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList
import com.zihany.weather.mvvm.view.WeatherBackground
import com.zihany.weather.mvvm.view.WeatherContent
import com.zihany.weather.mvvm.viewmodel.WeatherPageViewModel
import com.zihany.weather.ui.WeatherApp

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val COARSE_LOCATION = "coarse_location"
        private const val FINE_LOCATION = "fine_location"
    }

    private val viewModel by viewModels<WeatherPageViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.location.observe(this) {
            viewModel.getNowWeather(it)
            viewModel.getHourlyWeather(it)
            viewModel.getDailyForecastData(it)
            viewModel.getCityInfo(it)
        }

        setContent {
            WeatherApp(viewModel)
        }
    }

    @ExperimentalAnimationApi
    @Composable
    @Preview
    private fun WeatherPage() {
        val baseWeather: StandardCurrentWeather by viewModel.baseWeatherLiveData.observeAsState(
            StandardCurrentWeather()
        )
        val dailyWeatherList: StandardDailyWeatherList by viewModel.allWeatherLiveData.observeAsState(
            StandardDailyWeatherList()
        )
        val hourlyWeatherList: StandardHourlyWeatherList by viewModel.hourlyWeatherLiveData.observeAsState(
            StandardHourlyWeatherList()
        )
        val cityInfo: LocationData by viewModel.cityInfo.observeAsState(
            LocationData()
        )
        Surface(color = MaterialTheme.colors.background) {
            WeatherBackground(baseWeather)
            WeatherContent(
                baseWeather,
                dailyWeatherList,
                hourlyWeatherList,
                cityInfo
            )
        }
    }
}

