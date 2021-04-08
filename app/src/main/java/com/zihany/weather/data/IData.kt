package com.zihany.weather.data

interface IData {
    fun isSuccessful(): Boolean

    fun getStatus(): Int
}