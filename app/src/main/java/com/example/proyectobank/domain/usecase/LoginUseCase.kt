package com.example.proyectobank.domain.usecase

import com.example.proyectobank.domain.model.User

class LoginUseCase {
    private val mockDocumentNumber = "123456789"
    private val mockPassword = "1234"

    fun execute(user: User): Boolean {
        return user.documentNumber == mockDocumentNumber && user.password == mockPassword
    }
}
