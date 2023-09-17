package com.eldirohmanur.chicagoartgallery.domain.di

import com.eldirohmanur.chicagoartgallery.data.source.ArtworkRepositoryImpl
import com.eldirohmanur.chicagoartgallery.domain.repository.ArtworkRepository
import com.eldirohmanur.chicagoartgallery.domain.usecase.ArtworkInteractor
import com.eldirohmanur.chicagoartgallery.domain.usecase.ArtworkUseCase
import org.koin.dsl.module

val appDomainModule = module {
    single<ArtworkRepository> {
        ArtworkRepositoryImpl(get())
    }
    single<ArtworkUseCase> {
        ArtworkInteractor(get())
    }
}