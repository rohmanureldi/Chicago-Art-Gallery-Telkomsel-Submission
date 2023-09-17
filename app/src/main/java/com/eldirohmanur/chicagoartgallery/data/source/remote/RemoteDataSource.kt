package com.eldirohmanur.chicagoartgallery.data.source.remote

import com.eldirohmanur.chicagoartgallery.core.network.response.BasePaginationResponse
import com.eldirohmanur.chicagoartgallery.core.network.response.BaseResponse
import com.eldirohmanur.chicagoartgallery.data.source.remote.api.ArtworkApi

class RemoteDataSource(
    private val api: ArtworkApi
) {
    suspend fun getArtworks(page: Int): BasePaginationResponse {
        return api.getArtworks(page)
    }

    suspend fun getArtworkDetail(id: Long): BaseResponse {
        return api.getArtworkDetail(id)
    }

    suspend fun searchArtwork(query: String): BasePaginationResponse {
        return api.searchArtworks(query = query)
    }
}