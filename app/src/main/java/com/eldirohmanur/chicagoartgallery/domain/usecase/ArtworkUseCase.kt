package com.eldirohmanur.chicagoartgallery.domain.usecase

import com.eldirohmanur.chicagoartgallery.core.domain.Resource
import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO

interface ArtworkUseCase {
    suspend fun getArtworks(page: Int): Resource<List<ArtworkDTO>>
    suspend fun searchArtworks(query: String): Resource<List<ArtworkDTO>>
}