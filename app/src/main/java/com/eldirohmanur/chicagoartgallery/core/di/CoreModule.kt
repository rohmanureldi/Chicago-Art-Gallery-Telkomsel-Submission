package com.eldirohmanur.chicagoartgallery.core.di

import com.eldirohmanur.chicagoartgallery.core.secured.Secured
import org.koin.dsl.module

val coreModule = module {
    single { Secured }
}