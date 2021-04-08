package com.zihany.network.data

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val code: Int, val message: String) : RequestResult<Nothing>()
}

/**
 * `true` if [RequestResult] is of type [Success] & holds non-null [Success.data].
 */
val RequestResult<*>.succeeded
    get() = this is RequestResult.Success && data != null

fun <T> RequestResult<T>.successOr(fallback: T): T {
    return (this as? RequestResult.Success<T>)?.data ?: fallback
}