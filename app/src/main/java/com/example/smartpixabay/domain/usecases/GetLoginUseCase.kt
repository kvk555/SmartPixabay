package com.example.smartpixabay.domain.usecases

import com.example.smartpixabay.data.remotelogin.LoginRemoteService
import com.example.smartpixabay.domain.entities.LoggedUser

class GetLoginUseCase(
    private val loginService: LoginRemoteService
) : LoginUseCase {

    override suspend fun login(user: LoggedUser): Boolean {
        return loginService.login(user)
    }
}
