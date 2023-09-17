package com.eldirohmanur.chicagoartgallery.core.secured

object Secured {
    init {
        System.loadLibrary("chicagoartgallery");
    }

    external fun getBaseUrl(): String
}