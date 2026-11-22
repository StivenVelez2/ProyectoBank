package com.example.proyectobank.domain.model

// TODO: adjust fields to the Firestore schema defined by the team
data class Account(
    val accountNumber: String = "",
    val ownerDocumentNumber: String = "",
    val balance: Double = 0.0
)
