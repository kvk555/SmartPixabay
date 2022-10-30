package com.example.smartpixabay.domain.data

data class ImageEntity(
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
