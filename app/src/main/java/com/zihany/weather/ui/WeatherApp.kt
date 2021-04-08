package com.zihany.weather.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.zihany.weather.mvvm.view.WeatherScreen
import com.zihany.weather.mvvm.viewmodel.WeatherPageViewModel
import com.zihany.weather.ui.theme.WeatherTheme

@ExperimentalAnimationApi
@Composable
fun WeatherApp(viewModel: WeatherPageViewModel) {
    WeatherTheme {
        AppContent(viewModel)
    }
}

@ExperimentalAnimationApi
@Composable
private fun AppContent(viewModel: WeatherPageViewModel) {
    WeatherScreen(viewModel)
}