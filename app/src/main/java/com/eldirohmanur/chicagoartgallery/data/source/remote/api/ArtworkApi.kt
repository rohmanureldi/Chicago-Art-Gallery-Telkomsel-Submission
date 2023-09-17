package com.eldirohmanur.chicagoartgallery.data.source.remote.api

import com.eldirohmanur.chicagoartgallery.core.network.response.BasePaginationResponse
import com.eldirohmanur.chicagoartgallery.core.network.response.BaseResponse
import com.eldirohmanur.chicagoartgallery.data.source.remote.response.ArtworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtworkApi {

    @GET("artworks")
    suspend fun getArtworks(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int = 15
    ): BasePaginationResponse

    @GET("artworks/search")
    suspend fun searchArtworks(
        @Query("q") query: String,
        @Query("size") sizeReturn: Int = 15
    ): BasePaginationResponse

    @GET("artworks/{id}")
    suspend fun getArtworkDetail(
        @Path("id") id: Long
    ): BaseResponse
}