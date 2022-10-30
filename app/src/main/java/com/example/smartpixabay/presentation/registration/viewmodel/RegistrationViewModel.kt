package com.example.smartpixabay.presentation.registration.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpixabay.R
import com.example.smartpixabay.domain.entities.RegisteredUser
import com.example.smartpixabay.domain.usecases.RegistrationUseCase
import com.example.smartpixabay.presentation.registration.view.data.RegistrationFormState
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val registrationForm = MutableLiveData<RegistrationFormState>()
    val registrationFormState: LiveData<RegistrationFormState> = registrationForm

    private val registrationState = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> = registrationState

    fun register(email: String, password: String, age: String) {
        viewModelScope.launch {
            registrationState.value =
                registrationUseCase.register(RegisteredUser(email, password, age))
        }
    }

    fun registrationDataChanged(username: String, password: String, age: Int) {
        if (!isUserNameValid(username)) {
            registrationForm.value =
                RegistrationFormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            registrationForm.value =
                RegistrationFormState(passwordError = R.string.invalid_password)
        } else if (!isAgeValid(age)) {
            registrationForm.value =
                RegistrationFormState(ageError = R.string.invalid_age)
        } else {
            registrationForm.value = RegistrationFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(username).matches() && username.isNotBlank()

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 6..12
    }

    private fun isAgeValid(age: Int): Boolean {
        return age in 18..99
    }
}
