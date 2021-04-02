package com.zihany.network.data

import com.zihany.network.BaseRetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * 将请求得到的数据封装在@data中
 */
data class Response<out T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T,

    )

suspend fun <T : Any> Response<T>.execute(
    successBlock: (suspend CoroutineScope.() -> Unit)? = null,
    errorBlock: (suspend CoroutineScope.() -> Unit)? = null
) {
    return coroutineScope {
        if (errorCode == BaseRetrofitClient.FAIL) {
            errorBlock?.let { it() }
            RequestResult.Error(IOException(errorMsg))
        } else {
            successBlock?.let { it() }
            RequestResult.Success(data)
        }
    }
}

suspend fun <T : Any> Response<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): Response<T> {
    return coroutineScope {
        if (errorCode == BaseRetrofitClient.SUCCESS) {
            successBlock?.invoke(this, this@doSuccess.data)
        }
        this@doSuccess
    }
}

suspend fun <T : Any> Response<T>.doError(errorBlock: (suspend CoroutineScope.(String) -> Unit)? = null): Response<T> {
    return coroutineScope {
        if (errorCode == BaseRetrofitClient.FAIL) {
            errorBlock?.invoke(this, this@doError.errorMsg)
        }
        this@doError
    }
}
