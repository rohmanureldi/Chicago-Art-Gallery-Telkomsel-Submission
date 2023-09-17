package com.eldirohmanur.chicagoartgallery.domain.model

import com.eldirohmanur.chicagoartgallery.data.source.remote.response.ArtworkResponse

data class ArtworkDTO(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String,
    val artistDisplay: String
) {
    constructor(response: ArtworkResponse?, iiifUrl: String): this(
        id = response?.id ?: 0,
        title = response?.title ?: response?.artistDisplay ?: "Unknown Title",
        description = response?.description.orEmpty(),
        imageUrl = "$iiifUrl/${response?.imageId.orEmpty()}/full/843,/0/default.jpg",
        artistDisplay = response?.artistDisplay.orEmpty()
    )

    companion object {
        fun getDummy() = ArtworkDTO(
            id = 1234,
            title = "Artwork Title",
            description = "Artwork Description",
            imageUrl = "",
            artistDisplay = "Artist Name"
        )
    }
}
