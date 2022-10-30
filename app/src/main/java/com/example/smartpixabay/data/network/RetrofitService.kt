package com.example.smartpixabay.data.network

import com.example.smartpixabay.data.network.dto.ImagesContainer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pixabay.com/"

interface PixabayApi {

    @GET("api")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("image_type") imageType: String
    ): ImagesContainer
}

object PixabayRetrofit {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val pictureService = retrofit.create(PixabayApi::class.java)
}
