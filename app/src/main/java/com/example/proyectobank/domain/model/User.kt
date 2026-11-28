package com.example.proyectobank.domain.model

data class User(
    val uid: String = "",
    val documentNumber: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = ""
)
