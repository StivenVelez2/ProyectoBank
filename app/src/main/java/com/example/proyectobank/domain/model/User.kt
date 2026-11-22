package com.example.proyectobank.domain.model

data class User(
    val documentNumber: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = ""
)
