package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.domain.entities.ImageEntity

interface ImageDetailUseCase {
    suspend fun getImage(id: String): ImageEntity
}
