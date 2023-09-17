package com.eldirohmanur.chicagoartgallery.presentation.di

import com.eldirohmanur.chicagoartgallery.presentation.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appPresentationModule = module {
    viewModel {
        MainActivityViewModel(get())
    }
}