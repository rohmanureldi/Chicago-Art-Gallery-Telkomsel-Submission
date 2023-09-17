package com.eldirohmanur.chicagoartgallery.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ConfigResponse(
    @SerializedName("iiif_url") val iiifUrl: String
)
