package com.example.smartpixabay.domain.entities

data class LoggedUser(
    val email: String,
    val password: String
) {
    override fun toString() = "email=$email, password=$password,"
}
