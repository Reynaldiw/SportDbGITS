package com.reynaldiwijaya.sportdbgits.di

import com.reynaldiwijaya.sportdbgits.BuildConfig
import com.reynaldiwijaya.sportdbgits.data.footballclub.remote.FootballApiClient
import com.reynaldiwijaya.sportdbgits.data.lib.ParameterInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { ParameterInterceptor() }
    single { createOkHttpClient(get()) }
    single { createWebService<FootballApiClient>(get(), BuildConfig.BASE_URL) }
}

fun createOkHttpClient(interceptor: ParameterInterceptor) : OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val timeout = 120L

    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url : String) : T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}