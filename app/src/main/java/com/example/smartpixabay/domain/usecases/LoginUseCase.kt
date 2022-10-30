package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.domain.entities.LoggedUser

interface LoginUseCase {
    suspend fun login(user: LoggedUser): Boolean
}
