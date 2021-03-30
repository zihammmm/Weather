package com.zihany.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.zihany.weather.data.BasicWeather
import com.zihany.weather.data.TwentyFourHour
import com.zihany.weather.data.Weather
import com.zihany.weather.data.WeekWeather
import com.zihany.weather.mvvm.model.WeatherPageModel
import com.zihany.weather.mvvm.viewmodel.WeatherPageViewModel
import com.zihany.weather.ui.theme.MyTheme
import com.zihany.weather.utils.getDefaultDate
import com.zihany.weather.utils.transparentStatusBar
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.glide.LocalRequestManager

class MainActivity : ComponentActivity() {
    private val viewModel = WeatherPageViewModel(WeatherPageModel())

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setContent {
            MyTheme {
                WeatherPage()
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    @Preview("Light Theme", widthDp = 360, heightDp = 640)
    fun WeatherPage() {
        Surface(color = MaterialTheme.colors.background) {
            val weather = viewModel.getWeather()
            WeatherBackground(weather)
            WeatherContent(weather)
        }
    }

    @Composable
    fun WeatherBackground(weather: Weather) {
        Box {
            Image(
                painter = painterResource(id = weather.background),
                contentDescription = stringResource(id = weather.weather),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            val context = LocalContext.current
            val glide = Glide.with(context)
            CompositionLocalProvider(LocalRequestManager provides glide) {
                GlideImage(
                    data = weather.backgroundGif,
                    contentDescription = stringResource(id = weather.weather),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun WeatherContent(weather: Weather) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(scrollState)
        ) {
            WeatherBasic(weather = weather, scrollState = scrollState)
            WeatherDetails(weather = weather)
            WeatherWeek(weather = weather)
            WeatherOther(weather = weather)
        }
    }


    @Composable
    fun WeatherDetails(weather: Weather) {
        val twentyFourHours = weather.twentyFourHours
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(twentyFourHours) { twentyFourHour ->
                WeatherHour(twentyFourHour)
            }
        }
    }

    @Composable
    fun WeatherWeek(weather: Weather) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .padding(horizontal = 10.dp)
        ) {
            for (weekWeather in weather.weekWeathers) {
                WeatherWeekDetails(weekWeather = weekWeather)
            }
        }
    }

    @Composable
    fun WeatherWeekDetails(weekWeather: WeekWeather) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            Text(
                text = weekWeather.weekStr,
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .weight(1f),
                color = Color.White, fontSize = 15.sp
            )
            Image(
                painter = painterResource(id = weekWeather.icon),
                contentDescription = weekWeather.temperature,
                modifier = Modifier
                    .size(25.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .weight(1f)
            )
            Text(
                text = weekWeather.temperature,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .weight(1f),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }

    @Composable
    fun WeatherOther(weather: Weather) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .padding(horizontal = 10.dp)
        ) {
            for (weekWeather in weather.basicWeathers) {
                WeatherOtherDetails(weekWeather = weekWeather)
            }
        }
    }

    @Composable
    fun WeatherOtherDetails(weekWeather: BasicWeather) {
        Column(
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Text(
                text = stringResource(id = weekWeather.name),
                modifier = Modifier.padding(top = 5.dp),
                style = MaterialTheme.typography.caption,
                color = Color.LightGray
            )
            Text(
                text = weekWeather.value,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

    @Composable
    fun WeatherHour(twentyFourHour: TwentyFourHour) {
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
}

@ExperimentalAnimationApi
@Composable
fun WeatherBasic(weather: Weather, scrollState: ScrollState) {
    val offset = scrollState.value / 2
    val fontSize = (100f / offset * 70).coerceAtLeast(30f).coerceAtMost(75f).sp
    val modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .graphicsLayer { translationY = offset.toFloat() }
    val context = LocalContext.current

    Text(
        text = stringResource(id = weather.city),
        fontSize = 20.sp,
        color = Color.White,
        modifier = modifier.padding(top = 100.dp, bottom = 5.dp)
    )
    AnimatedVisibility(visible = fontSize == 75f.sp) {
        Text(
            text = "${weather.currentTemperature}°",
            fontSize = fontSize,
            color = Color.White,
            modifier = modifier.padding(top = 2.5.dp)
        )
    }
    Text(
        text = context.getDefaultDate(System.currentTimeMillis()),
        modifier = modifier.padding(top = 45.dp, start = 10.dp),
        fontSize = 16.sp,
        color = Color.White
    )
}