package com.zihany.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.zihany.weather.data.location.LocationData
import com.zihany.weather.data.standard.StandardCurrentWeather
import com.zihany.weather.data.standard.StandardDailyWeatherList
import com.zihany.weather.data.standard.StandardHourlyWeatherList
import com.zihany.weather.mvvm.view.WeatherBackground
import com.zihany.weather.mvvm.view.WeatherContent
import com.zihany.weather.mvvm.viewmodel.WeatherPageViewModel
import com.zihany.weather.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val COARSE_LOCATION = "coarse_location"
        private const val FINE_LOCATION = "fine_location"
    }

    private val viewModel by viewModels<WeatherPageViewModel>()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            map.entries.forEach {
                if (!it.value) {
                    Log.d(TAG, "permission ${it.key} denied")
                }else {
                    Log.d(TAG, "permission ${it.key} granted")
                }
            }
        }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //transparentStatusBar()
        //requestPermission()

        viewModel.location.observe(this) {
            viewModel.getNowWeather(it)
            viewModel.getHourlyWeather(it)
            viewModel.getDailyForecastData(it)
            viewModel.getCityInfo(it)
        }

//        viewModel.getBaseWeather()
//        viewModel.getAllWeather()
        setContent {
            MyTheme {
                WeatherPage()
            }
        }
    }


    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d(TAG, "start locate")
            }

            shouldShowRequestPermissionRationale("请求定位权限") -> {
                Toast.makeText(this, "请求定位权限", Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                Log.d(TAG, "request permission")
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
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
        Log.d(TAG, "now: $baseWeather")
        Log.d(TAG, "hourly: $hourlyWeatherList")
        Log.d(TAG, "daily: $dailyWeatherList")
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

