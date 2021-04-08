package com.zihany.weather.mvvm.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.zihany.weather.R
import com.zihany.weather.data.location.LocationData
import com.zihany.weather.data.standard.*
import com.zihany.weather.mvvm.viewmodel.WeatherPageViewModel
import com.zihany.weather.utils.getDefaultDate
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.glide.LocalRequestManager


@ExperimentalAnimationApi
@Composable
fun WeatherScreen(weatherPageViewModel: WeatherPageViewModel) {

    val baseWeather = weatherPageViewModel.baseWeatherLiveData.observeAsState(StandardCurrentWeather())
    val dailyWeatherList = weatherPageViewModel.allWeatherLiveData.observeAsState(StandardDailyWeatherList())
    val hourlyWeatherList = weatherPageViewModel.hourlyWeatherLiveData.observeAsState(StandardHourlyWeatherList())
    val cityInfo = weatherPageViewModel.cityInfo.observeAsState(LocationData())

    Surface(color = MaterialTheme.colors.background) {
        WeatherBackground(baseWeather.value)
        WeatherContent(
            baseWeather.value,
            dailyWeatherList.value,
            hourlyWeatherList.value,
            cityInfo.value
        )
    }
}

@Composable
fun WeatherBackground(weather: StandardCurrentWeather) {
    Box {
        Image(
            painter = painterResource(id = weather.background),
            contentDescription = weather.weather,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        val context = LocalContext.current
        val glide = Glide.with(context)
        CompositionLocalProvider(LocalRequestManager provides glide) {
            GlideImage(
                data = weather.backgroundGif,
                contentDescription = weather.weather,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherContent(
    currentWeather: StandardCurrentWeather,
    dailyWeather: StandardDailyWeatherList,
    hourlyWeatherList: StandardHourlyWeatherList,
    location: LocationData
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "添加新地点",
            modifier = Modifier.padding(start = 15.dp)
        )
        Text(
            text = location.location[0].name,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(align = Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 20.sp
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        WeatherBasic(weather = currentWeather, scrollState = scrollState)
        WeatherDetails(weather = hourlyWeatherList)
        WeatherWeek(weather = dailyWeather)
        WeatherOther(weather = currentWeather)
    }
}

@Composable
fun WeatherDetails(weather: StandardHourlyWeatherList) {
    val twentyFourHours = weather.hourlyWeatherList
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        for (hourlyWeather in twentyFourHours) {
            item {
                WeatherHour(twentyFourHour = hourlyWeather)
            }
        }
    }
}

@Composable
fun WeatherWeek(weather: StandardDailyWeatherList) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp)
            .padding(horizontal = 10.dp)
    ) {
        for (forcast in weather.allWeatherData) {
            WeatherWeekDetails(weekWeather = forcast)
        }
    }
}

@Composable
fun WeatherWeekDetails(weekWeather: StandardDailyWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Text(
            text = weekWeather.week,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(1f),
            color = Color.White, fontSize = 15.sp
        )
        Image(
            painter = painterResource(id = weekWeather.icon),
            contentDescription = weekWeather.dayWeather,
            modifier = Modifier
                .size(35.dp)
                .wrapContentSize()
                .weight(1f)
        )
        Text(
            text = "${weekWeather.dayTemp}°",
            modifier = Modifier
                .padding(start = 25.dp)
                .weight(1f),
            color = Color.White,
            fontSize = 15.sp
        )
        Text(
            text = "${weekWeather.nightTemp}°",
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .weight(1f),
            color = Color.LightGray,
            fontSize = 15.sp,
        )
    }
}

@Composable
fun WeatherOther(weather: StandardCurrentWeather) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp)
    ) {
        for (basicWeather in weather.weatherDetails) {
            WeatherOtherDetails(basiceather = basicWeather)
        }
    }
}

@Composable
fun WeatherOtherDetails(basiceather: BasicWeatherDetails) {
    Column(
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        Divider(color = Color.Gray, thickness = 0.5.dp)
        Text(
            text = basiceather.name,
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.caption,
            color = Color.LightGray
        )
        Text(
            text = basiceather.value,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
fun WeatherHour(twentyFourHour: StandardHourlyWeather) {
    val modifier = Modifier.padding(top = 9.dp)
    Column(
        modifier = Modifier.width(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = twentyFourHour.time,
            color = Color.White,
            fontSize = 15.sp,
            modifier = modifier
        )
        Image(
            painter = painterResource(id = twentyFourHour.icon),
            contentDescription = twentyFourHour.temperature,
            modifier = modifier.size(25.dp)
        )
        Text(
            text = twentyFourHour.temperature,
            color = Color.White,
            modifier = modifier,
            fontSize = 15.sp
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun WeatherBasic(weather: StandardCurrentWeather, scrollState: ScrollState) {
    val offset = scrollState.value / 2
    val fontSize = (100f / offset * 70).coerceAtLeast(30f).coerceAtMost(75f).sp
    val modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .wrapContentSize(Alignment.Center)
        .graphicsLayer { translationY = offset.toFloat() }
    val context = LocalContext.current

    AnimatedVisibility(visible = fontSize == 75f.sp) {
        Text(
            text = "${weather.currentTemperature}°",
            fontSize = fontSize,
            color = Color.White,
            modifier = modifier.padding(top = 100.dp)
        )
    }
    Text(
        text = weather.weather,
        fontSize = 20.sp,
        color = Color.White,
        modifier = modifier.padding(top = 2.5.dp)
    )

    Text(
        text = context.getDefaultDate(System.currentTimeMillis()),
        modifier = modifier,
        fontSize = 16.sp,
        color = Color.White
    )
}