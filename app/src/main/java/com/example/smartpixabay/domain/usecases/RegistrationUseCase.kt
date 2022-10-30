package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.domain.entities.RegisteredUser

interface RegistrationUseCase {
    suspend fun register(user: RegisteredUser): Boolean
}
