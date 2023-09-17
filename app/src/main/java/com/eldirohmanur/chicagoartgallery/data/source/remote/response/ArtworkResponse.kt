package com.eldirohmanur.chicagoartgallery.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ArtworkResponse(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image_id") val imageId: String? = null,
    @SerializedName("artist_display") val artistDisplay: String? = null
)
