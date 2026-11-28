package com.example.proyectobank.presentation.login

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.proyectobank.data.auth.FirestoreAuthRepositoryImpl
import com.example.proyectobank.domain.exception.AuthException
import com.example.proyectobank.domain.usecase.LoginUseCase

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val loginUseCase = LoginUseCase(FirestoreAuthRepositoryImpl(application))

    val documentNumber = mutableStateOf("")
    val password = mutableStateOf("")
    val error = mutableStateOf<LoginError?>(null)
    val isLoading = mutableStateOf(false)

    fun changeDocumentNumber(newDocumentNumber: String) {
        documentNumber.value = newDocumentNumber
        error.value = null
    }

    fun changePassword(newPassword: String) {
        password.value = newPassword
        error.value = null
    }

    fun login(onSuccess: () -> Unit) {
        val document = documentNumber.value.trim()
        val pass = password.value

        if (document.isEmpty() || pass.isEmpty()) {
            error.value = LoginError.EMPTY_FIELDS
            return
        }
        if (document.toLongOrNull() == null) {
            error.value = LoginError.INVALID_DOCUMENT
            return
        }

        isLoading.value = true
        error.value = null

        loginUseCase.execute(
            documentNumber = document,
            password = pass,
            onSuccess = {
                isLoading.value = false
                onSuccess()
            },
            onFailure = { exception ->
                isLoading.value = false
                error.value = if (exception is AuthException) {
                    LoginError.INVALID_CREDENTIALS
                } else {
                    LoginError.GENERAL
                }
            }
        )
    }
}
