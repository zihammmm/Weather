package com.zihany.weather.request

import org.junit.Test

internal class ApiRequestTest {

    @Test
    fun testGetBaseWeather() {
        ApiRequest.requestBaseWeather(110101)
    }
}