package com.zihany.network

import com.zihany.network.data.RequestResult
import com.zihany.network.data.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

open class IRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> RequestResult<T>,
        errorMessage: String
    ): RequestResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            RequestResult.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> execute(
        response: Response<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ):RequestResult<T> {
        return coroutineScope {
            if (response.errorCode == BaseRetrofitClient.FAIL) {
                errorBlock?.let { it() }
                RequestResult.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let { it() }
                RequestResult.Success(response.data)
            }
        }
    }
}