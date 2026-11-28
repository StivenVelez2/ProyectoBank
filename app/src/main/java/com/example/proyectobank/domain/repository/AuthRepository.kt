package com.example.proyectobank.domain.repository

import com.example.proyectobank.domain.model.User

interface AuthRepository {
    fun currentUserId(): String?

    fun register(
        user: User,
        password: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun login(
        email: String,
        password: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun logout()
}
