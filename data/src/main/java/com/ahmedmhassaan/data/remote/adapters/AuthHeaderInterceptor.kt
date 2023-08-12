package com.ahmedmhassaan.data.remote.adapters

import com.ahmedmhassaan.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.KEY}")
            .build()

//        val requestBuilder = original.newBuilder().url(url)


        return chain.proceed(request)
    }
}