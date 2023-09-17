package com.eldirohmanur.chicagoartgallery.domain.usecase

import com.eldirohmanur.chicagoartgallery.core.domain.Resource
import com.eldirohmanur.chicagoartgallery.core.network.ApiResponse
import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO
import com.eldirohmanur.chicagoartgallery.domain.repository.ArtworkRepository

class ArtworkInteractor(
    private val repo: ArtworkRepository
): ArtworkUseCase {
    override suspend fun getArtworks(page: Int): Resource<List<ArtworkDTO>> {
        val response = repo.getArtwork(page)
        return if (response is ApiResponse.Success) {
            Resource.Success(response.data)
        } else {
            response as ApiResponse.Error
            Resource.Error(response.errorMessage)
        }
    }

    override suspend fun searchArtworks(query: String): Resource<List<ArtworkDTO>> {
        val response = repo.searchArtwork(query)

        return if (response is ApiResponse.Success) {
            Resource.Success(response.data)
        } else {
            response as ApiResponse.Error
            Resource.Error(response.errorMessage)
        }
    }
}