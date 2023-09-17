package com.eldirohmanur.chicagoartgallery.data.di

import com.eldirohmanur.chicagoartgallery.data.source.remote.RemoteDataSource
import com.eldirohmanur.chicagoartgallery.data.source.remote.api.ArtworkApi
import org.koin.dsl.module
import retrofit2.Retrofit

val appDataModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(ArtworkApi::class.java)
    }

    single {
        RemoteDataSource(get())
    }
}