package com.eldirohmanur.chicagoartgallery.data.source

import com.eldirohmanur.chicagoartgallery.core.network.ApiResponse
import com.eldirohmanur.chicagoartgallery.data.source.remote.RemoteDataSource
import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO
import com.eldirohmanur.chicagoartgallery.domain.repository.ArtworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class ArtworkRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): ArtworkRepository {
    override suspend fun getArtwork(page: Int): ApiResponse<List<ArtworkDTO>> {
        return try {
            val response = remoteDataSource.getArtworks(page)
            val dto = response.data.map { ArtworkDTO(it, response.config?.iiifUrl.orEmpty()) }
            ApiResponse.Success(dto)
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: e.localizedMessage)
        }
    }

    override suspend fun getArtworkDetail(id: Long): ApiResponse<ArtworkDTO> {
        return try {
            val response = remoteDataSource.getArtworkDetail(id)
            ApiResponse.Success(ArtworkDTO(response.data, response.config?.iiifUrl.orEmpty()))
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: e.localizedMessage)
        }
    }

    override suspend fun searchArtwork(query: String): ApiResponse<List<ArtworkDTO>> {
        return try {
            val response = remoteDataSource.searchArtwork(query).data
            val deferredItems = response.filter { it.id != null }.map {
                withContext(Dispatchers.IO) {
                    async { getArtworkDetail(it.id!!) }
                }
            }

            val mappedResponse = deferredItems.awaitAll().filterIsInstance<ApiResponse.Success<ArtworkDTO>>()
            val mappedArtwork = mappedResponse.map {
                it.data
            }

            ApiResponse.Success(mappedArtwork)
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: e.localizedMessage)
        }
    }
}