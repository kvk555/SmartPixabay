package com.example.smartpixabay.presentation.imagedetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpixabay.domain.entities.ImageEntity
import com.example.smartpixabay.domain.usecases.ImageDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageDetailViewModel(
    private val usaCase: ImageDetailUseCase
) : ViewModel() {

    val image = MutableLiveData<ImageEntity>()

    fun getImageById(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                image.postValue(usaCase.getImage(id))
            }
        }
    }
}
