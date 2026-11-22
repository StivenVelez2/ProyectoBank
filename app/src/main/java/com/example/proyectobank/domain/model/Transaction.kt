package com.example.proyectobank.domain.model

// TODO: adjust fields to the Firestore schema defined by the team
data class Transaction(
    val id: String = "",
    val originAccountNumber: String = "",
    val destinationAccountNumber: String = "",
    val amount: Double = 0.0,
    val timestamp: Long = 0L
)
