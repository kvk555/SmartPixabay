package com.example.smartpixabay.presentation.registration.view.data

/**
 * Data validation state of the login form.
 */
data class RegistrationFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val ageError: Int? = null,
    val isDataValid: Boolean = false
)
