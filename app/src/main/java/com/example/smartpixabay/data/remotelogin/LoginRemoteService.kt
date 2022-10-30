package com.example.smartpixabay.data.common

import com.example.smartpixabay.domain.data.LoggedUser
import com.example.smartpixabay.domain.data.RegisteredUser


/**
 * For imitation remote login requests and response
 */
interface LoginRemoteService {

    suspend fun login(user: LoggedUser): Boolean
    suspend fun register(user: RegisteredUser): Boolean
}
