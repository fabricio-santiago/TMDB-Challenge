package com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util

import com.santiago.fabricio.tmdbchallenge.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(Constants.LANGUAGUE_PARAM, Constants.LANGUAGUE_VALUE)
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}