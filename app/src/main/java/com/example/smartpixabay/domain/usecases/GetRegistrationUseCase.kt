package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.data.remotelogin.LoginRemoteService
import com.example.smartpixabay.domain.entities.RegisteredUser

class GetRegistrationUseCase(
    private val loginService: LoginRemoteService
) : RegistrationUseCase {

    override suspend fun register(user: RegisteredUser): Boolean {
        return loginService.register(user)
    }
}
