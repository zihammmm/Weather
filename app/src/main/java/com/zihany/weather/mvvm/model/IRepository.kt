package com.zihany.weather.mvvm.model

import com.zihany.network.data.RequestResult
import com.zihany.weather.data.IData
import com.zihany.weather.request.ERROR_CODE_EXCEPTION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

interface IRepository {

    /**
     * 第一步
     * 请求数据，并将raw data封装成RequestResult<IData>
     */
    suspend fun <T : IData> safeApiCall(
        call: suspend () -> T,
        errorMessage: String = "no errorMessage"
    ): RequestResult<T> {
        return try {
            val data = call()
            RequestResult.Success(data)
        } catch (e: Exception) {
            RequestResult.Error(ERROR_CODE_EXCEPTION, e.message.toString())
        }
    }

    /**
     * 根据@param response的类型执行对应的函数
     */
    suspend fun <T : IData> execute(
        response: RequestResult<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): RequestResult<T> {
        return coroutineScope {
            if (response is RequestResult.Success) {
                if (response.data.isSuccessful()) {
                    successBlock?.let { it() }
                    response
                } else {
                    errorBlock?.let { it() }
                    RequestResult.Error(code = response.data.getStatus(), message = response.data.toString())
                }
            } else {
                errorBlock?.let { it() }
                response
            }
        }
    }
}