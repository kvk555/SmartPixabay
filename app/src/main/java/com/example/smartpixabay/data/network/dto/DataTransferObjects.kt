package com.example.smartpixabay.data.network.dto

import com.example.smartpixabay.data.database.DatabaseImage
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesContainer(val hits: List<Image>)

@JsonClass(generateAdapter = true)
data class Image(
    val id: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val webformatURL: String,
    val imageSize: Int,
    val views: Int,
    val likes: Int,
    val comments: Int,
    val downloads: Int,
    val user: String
)

fun ImagesContainer.toDatabaseEntity(): List<DatabaseImage> {
    return hits.map {
        DatabaseImage(
            id = it.id,
            type = it.type,
            tags = it.tags,
            previewURL = it.previewURL,
            webformatURL = it.webformatURL,
            imageSize = it.imageSize,
            views = it.views,
            likes = it.likes,
            comments = it.comments,
            downloads = it.downloads,
            user = it.user
        )
    }
}
