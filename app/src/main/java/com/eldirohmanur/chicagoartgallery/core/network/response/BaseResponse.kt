package com.eldirohmanur.chicagoartgallery.core.network.response

import com.eldirohmanur.chicagoartgallery.data.source.remote.response.ArtworkResponse
import com.eldirohmanur.chicagoartgallery.data.source.remote.response.ConfigResponse
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("data") val data: ArtworkResponse? = null,
    @SerializedName("config") val config: ConfigResponse? = null
)
