package com.example.smartpixabay.data.repository

import com.example.smartpixabay.BuildConfig
import com.example.smartpixabay.data.database.ImageDatabase
import com.example.smartpixabay.data.database.convertToImageEntity
import com.example.smartpixabay.data.network.PixabayRetrofit
import com.example.smartpixabay.data.network.dto.toDatabaseEntity
import com.example.smartpixabay.domain.entities.ImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val IMAGE_TYPE = "photo"

class DefaultImagesRepository(
    private val database: ImageDatabase
) : ImagesRepository {

    override suspend fun getImages(): List<ImageEntity> =
        database.imageDao.getImages().map { it.convertToImageEntity() }

    override suspend fun getImageById(id: String): ImageEntity {
        return database.imageDao.getImageById(id).convertToImageEntity()
    }

    override suspend fun refreshImages() {
        withContext(Dispatchers.IO) {
            val imagesList = PixabayRetrofit.pictureService.getImages(
                BuildConfig.API_KEY, IMAGE_TYPE
            )
            database.imageDao.insertAll(imagesList.toDatabaseEntity())
        }
    }
}
