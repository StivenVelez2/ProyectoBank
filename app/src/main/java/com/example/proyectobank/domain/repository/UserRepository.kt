package com.example.proyectobank.domain.repository

import com.example.proyectobank.domain.model.User

interface UserRepository {
    fun getUser(
        uid: String,
        onSuccess: (User?) -> Unit,
        onFailure: (Exception) -> Unit
    )
}
