package com.eldirohmanur.chicagoartgallery.core.network.response

import com.eldirohmanur.chicagoartgallery.data.source.remote.response.ArtworkResponse
import com.eldirohmanur.chicagoartgallery.data.source.remote.response.ConfigResponse
import com.google.gson.annotations.SerializedName

data class BasePaginationResponse(
    @SerializedName("pagination") val pagination: PaginationResponse? = null,
    @SerializedName("data") val data: List<ArtworkResponse> = emptyList(),
    @SerializedName("config") val config: ConfigResponse? = null
)
