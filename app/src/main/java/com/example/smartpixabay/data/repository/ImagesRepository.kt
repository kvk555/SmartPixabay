package com.example.smartpixabay.data.repository

import com.example.smartpixabay.domain.entities.ImageEntity

interface ImagesRepository {
    suspend fun getImages(): List<ImageEntity>
    suspend fun getImageById(id: String): ImageEntity
    suspend fun refreshImages()
}
