package com.it.taskforattractor.di

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.it.taskforattractor.BuildConfig
import com.it.taskforattractor.data.local.MockServer
import com.it.taskforattractor.data.local.Service
import com.it.taskforattractor.data.remote.ServiceApi
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L
private const val LOGGER_TAG = "OkHttp"
private const val baseUrl = BuildConfig.BASE_URL

val networkModule = module {
    single { createLoggingInterceptor() }

    single { createOkHttpClient(get()) }

    single { createRetrofit(get(), baseUrl) }

    single { createService(get()) }

    single<Service> { MockServer() }
}

fun createLoggingInterceptor(): LoggingInterceptor {
    return LoggingInterceptor.Builder()
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .tag(LOGGER_TAG)
        .build()
}

fun createOkHttpClient(logger: LoggingInterceptor): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder().apply {
        connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        readTimeout(TIME_OUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        addInterceptor(logger)
    }

    clientBuilder.addInterceptor { chain ->
//        val isNeedAuthHeader = chain.request().headers["isAuthorizable"] != "false"
        val newRequest = chain.request().newBuilder()
        chain.proceed(newRequest.build())
    }
    return clientBuilder.build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit = run {
    Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun createService(retrofit: Retrofit): ServiceApi = retrofit.create(ServiceApi::class.java)