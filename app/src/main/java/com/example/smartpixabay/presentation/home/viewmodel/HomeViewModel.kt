package com.example.smartpixabay.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpixabay.domain.entities.ImageEntity
import com.example.smartpixabay.domain.usecases.ImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class HomeViewModel(
    private val imageUseCase: ImageUseCase
) : ViewModel() {

    val imagesList = MutableLiveData<List<ImageEntity>>()

    private var isNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = isNetworkError

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                imageUseCase.refreshImages()
                imagesList.postValue(getImages())
                isNetworkError.value = false
            } catch (networkError: IOException) {
                val images = getImages()

                if (images.isEmpty()) {
                    isNetworkError.value = true
                } else {
                    imagesList.postValue(images)
                }
            }
        }
    }

    private suspend fun getImages(): List<ImageEntity> =
        withContext(Dispatchers.IO) {
            imageUseCase.getImages()
        }
}
