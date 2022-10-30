package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.data.repository.ImagesRepository
import com.example.smartpixabay.domain.entities.ImageEntity

class GetImageDetailUseCase(
    private val repository: ImagesRepository
) : ImageDetailUseCase {

    override suspend fun getImage(id: String): ImageEntity {
        return repository.getImageById(id)
    }
}
