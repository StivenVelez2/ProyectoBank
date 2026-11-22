package com.example.proyectobank.domain.model

// TODO: adjust fields to the Firestore schema defined by the team
data class User(
    val documentNumber: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phoneNumber: String = ""
)
