package com.nextint.alodokterbykelompok2.utils

import com.nextint.alodokterbykelompok2.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter(RemoteConstant.API_KEY_PARAM,BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}