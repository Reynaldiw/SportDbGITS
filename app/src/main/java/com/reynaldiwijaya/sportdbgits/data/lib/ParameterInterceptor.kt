package com.reynaldiwijaya.sportdbgits.data.lib

import okhttp3.Interceptor
import okhttp3.Response

class ParameterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var requestBody = chain.request()
        requestBody = requestBody.newBuilder()
            .build()
        return chain.proceed(requestBody)
    }
}