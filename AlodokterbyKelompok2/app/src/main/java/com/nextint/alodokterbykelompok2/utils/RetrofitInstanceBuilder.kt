package com.nextint.alodokterbykelompok2.utils

import com.nextint.alodokterbykelompok2.data.remote.AlodokterAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceBuilder {
    val RETROFIT_INSTANCE : AlodokterAPI by lazy {
        Retrofit.Builder()
            .baseUrl(RemoteConstant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AlodokterAPI::class.java)
    }

    private val okHttpClient : OkHttpClient by lazy {
        val client = OkHttpClient.Builder()
            client.addInterceptor(loggingInterceptor)
        client.addInterceptor(requestInterceptor)
            client.build()
    }

    private val requestInterceptor : Interceptor by lazy {
        RequestInterceptor()
    }

    private val loggingInterceptor : HttpLoggingInterceptor by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
}