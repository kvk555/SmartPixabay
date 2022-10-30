package com.example.smartpixabay.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.smartpixabay.domain.entities.ImageEntity

@Entity
data class DatabaseImage(
    @PrimaryKey
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

fun DatabaseImage.convertToImageEntity(): ImageEntity {
    return ImageEntity(
        id = id,
        type = type,
        tags = tags,
        previewURL = previewURL,
        webformatURL = webformatURL,
        imageSize = imageSize,
        views = views,
        likes = likes,
        comments = comments,
        downloads = downloads,
        user = user
    )
}
