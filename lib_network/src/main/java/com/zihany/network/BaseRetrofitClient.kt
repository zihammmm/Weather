package com.zihany.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 基础retrofit客户端
 * 该类保持不变
 */
class BaseRetrofitClient {
    companion object {
        const val FAIL = 0
        const val SUCCESS = 1
    }

    private val gson = Gson().newBuilder()
        .setLenient()
        .serializeNulls()
        .create()

    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val interceptor = HttpLoggingInterceptor { message -> println(message) }
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            response
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()
    
    val baseBuilder: Retrofit.Builder = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
}