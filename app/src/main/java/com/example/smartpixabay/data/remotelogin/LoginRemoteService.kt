package com.example.smartpixabay.data.remotelogin

import com.example.smartpixabay.domain.entities.LoggedUser
import com.example.smartpixabay.domain.entities.RegisteredUser

/**
 * For imitation remote login requests and response
 */
interface LoginRemoteService {

    suspend fun login(user: LoggedUser): Boolean
    suspend fun register(user: RegisteredUser): Boolean
}
