package com.eldirohmanur.chicagoartgallery.core.network.di

import com.eldirohmanur.chicagoartgallery.core.secured.Secured
import okhttp3.Dns
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val REQUEST_TIMEOUT = 1L

private val okHttpModule = module {
    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .dns(Dns.SYSTEM)
            .build()
    }

}

private val retrofitModule = module {
    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Secured.getBaseUrl())
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val coreNetworkModule = okHttpModule + retrofitModule

