package com.example.proyectobank.domain.usecase

import com.example.proyectobank.domain.model.User
import com.example.proyectobank.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    fun execute(
        documentNumber: String,
        password: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        authRepository.login(documentNumber, password, onSuccess, onFailure)
    }
}
