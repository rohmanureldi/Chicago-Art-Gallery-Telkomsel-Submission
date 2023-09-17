package com.eldirohmanur.chicagoartgallery.domain.repository

import com.eldirohmanur.chicagoartgallery.core.network.ApiResponse
import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO

interface ArtworkRepository {
    suspend fun getArtwork(page: Int): ApiResponse<List<ArtworkDTO>>
    suspend fun getArtworkDetail(id: Long): ApiResponse<ArtworkDTO>
    suspend fun searchArtwork(query: String): ApiResponse<List<ArtworkDTO>>
}