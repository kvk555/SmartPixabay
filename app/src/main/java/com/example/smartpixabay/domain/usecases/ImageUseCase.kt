package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.domain.entities.ImageEntity

interface ImageUseCase {

    suspend fun getImages(): List<ImageEntity>
    suspend fun refreshImages()
}
