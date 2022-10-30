package com.example.smartpixabay.domain.entities

data class RegisteredUser(
    val email: String,
    val password: String,
    val age: String
)

fun RegisteredUser.toComparableString(): String = "email=$email,"
