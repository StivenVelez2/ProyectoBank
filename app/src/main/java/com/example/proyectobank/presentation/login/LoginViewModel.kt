package com.example.proyectobank.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.proyectobank.domain.model.User
import com.example.proyectobank.domain.usecase.LoginUseCase

class LoginViewModel : ViewModel() {
    private val loginUseCase = LoginUseCase()

    val documentNumber = mutableStateOf("")
    val password = mutableStateOf("")
    val error = mutableStateOf<LoginError?>(null)

    fun changeDocumentNumber(newDocumentNumber: String) {
        documentNumber.value = newDocumentNumber
        error.value = null
    }

    fun changePassword(newPassword: String) {
        password.value = newPassword
        error.value = null
    }

    fun login(): Boolean {
        val document = documentNumber.value.trim()
        val pass = password.value

        if (document.isEmpty() || pass.isEmpty()) {
            error.value = LoginError.EMPTY_FIELDS
            return false
        }
        if (document.toLongOrNull() == null) {
            error.value = LoginError.INVALID_DOCUMENT
            return false
        }

        val user = User(documentNumber = document, password = pass)
        val isValidUser = loginUseCase.execute(user)
        if (!isValidUser) {
            error.value = LoginError.INVALID_CREDENTIALS
        }
        return isValidUser
    }
}
