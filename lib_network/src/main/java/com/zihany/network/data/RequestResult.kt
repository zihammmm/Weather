package com.zihany.network.data

sealed class RequestResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()
}
