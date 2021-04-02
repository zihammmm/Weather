package com.zihany.weather

import android.Manifest
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
import androidx.core.app.ActivityCompat
import com.zihany.weather.data.StandardCurrentWeather
import com.zihany.weather.data.StandardForecastWeatherList
import com.zihany.weather.mvvm.view.WeatherBackground
import com.zihany.weather.mvvm.view.WeatherContent
import com.zihany.weather.mvvm.viewmodel.WeatherPageViewModel
import com.zihany.weather.ui.theme.MyTheme
import com.zihany.weather.utils.transparentStatusBar

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<WeatherPageViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.INTERNET), 1
        )
        viewModel.getBaseWeather()
        viewModel.getAllWeather()
        setContent {
            MyTheme {
                WeatherPage()
            }
        }
    }


    @ExperimentalAnimationApi
    @Composable
    private fun WeatherPage() {
        val baseWeather: StandardCurrentWeather by viewModel.baseWeatherLiveData.observeAsState(
            StandardCurrentWeather()
        )
        val forecastWeatherList: StandardForecastWeatherList by viewModel.allWeatherLiveData.observeAsState(
            StandardForecastWeatherList()
        )
        Surface(color = MaterialTheme.colors.background) {
            WeatherBackground(baseWeather)
            WeatherContent(
                baseWeather,
                forecastWeatherList
            )
        }
    }
}

