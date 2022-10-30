package com.example.smartpixabay.presentation.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartpixabay.R
import com.example.smartpixabay.domain.entities.LoggedUser
import com.example.smartpixabay.domain.usecases.LoginUseCase
import com.example.smartpixabay.presentation.login.view.data.LoginFormState
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = loginForm

    private val loginState = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginState.value = loginUseCase.login(LoggedUser(email, password))
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            loginForm.value = LoginFormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(username).matches() && username.isNotBlank()

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 6..12
    }
}
