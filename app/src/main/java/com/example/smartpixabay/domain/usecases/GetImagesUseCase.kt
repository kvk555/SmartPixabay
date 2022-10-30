package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.data.repository.ImagesRepository
import com.example.smartpixabay.domain.entities.ImageEntity

class GetImagesUseCase(
    private val repository: ImagesRepository
) : ImageUseCase {

    override suspend fun getImages(): List<ImageEntity> {
        return repository.getImages()
    }

    override suspend fun refreshImages() {
        repository.refreshImages()
    }
}