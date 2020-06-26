package com.example.astropay.network

import com.example.astropay.BuildConfig
import com.example.astropay.services.QUERY_PARAM_APPID
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()

        val url = req
            .url()
            .newBuilder()
            .addQueryParameter(QUERY_PARAM_APPID, BuildConfig.API_KEY)
            .build()
        req = req.newBuilder().url(url).build()

        return chain.proceed(req)
    }

}