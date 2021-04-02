package com.zihany.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseRetrofitClient {
    const val key = "d600314655ba922e4b5389bacaad0ddf"
    private const val baseUrl = "https://restapi.amap.com/"

    const val FAIL = 0
    const val SUCCESS = 1

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

    private val instance = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val weatherClient: ApiService by lazy {
        instance.create(ApiService::class.java)
    }


}