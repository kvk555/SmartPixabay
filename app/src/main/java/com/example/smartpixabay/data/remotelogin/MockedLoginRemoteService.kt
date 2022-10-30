package com.example.smartpixabay.data.remotelogin

import android.content.Context
import com.example.smartpixabay.App
import com.example.smartpixabay.data.PREF_FILE_KEY
import com.example.smartpixabay.data.USERS_KEY
import com.example.smartpixabay.domain.entities.LoggedUser
import com.example.smartpixabay.domain.entities.RegisteredUser
import com.example.smartpixabay.domain.entities.toComparableString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MockedLoginRemoteService : LoginRemoteService {

    private val sharedPref by lazy {
        App.INSTANCE.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)
    }

    override suspend fun login(user: LoggedUser): Boolean {
        return withContext(Dispatchers.IO) {
            delay(2000)  // imitation remote request
            val users = getUsers()
            users.any { it.contains(user.toString()) }
        }
    }

    override suspend fun register(user: RegisteredUser): Boolean {
        return withContext(Dispatchers.IO) {
            delay(2000)  // imitation remote request
            val users = getUsers()
            if (users.any { it.contains(user.toComparableString()) }) {
                false
            } else {
                val updatedUsers = users.toMutableSet().apply { add(user.toString()) }
                val editor = sharedPref?.edit()
                editor?.putStringSet(USERS_KEY, updatedUsers)
                editor?.apply()
                true
            }
        }
    }

    private fun getUsers(): Set<String> {
        return sharedPref?.getStringSet(USERS_KEY, setOf()) ?: setOf()
    }
}
