package com.eldirohmanur.chicagoartgallery.core.network.response

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("current_page") val currentPage: Int
)
