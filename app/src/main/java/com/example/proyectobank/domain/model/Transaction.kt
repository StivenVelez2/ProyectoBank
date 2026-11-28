package com.example.proyectobank.domain.model

data class Transaction(
    val id: String = "",
    val originAccountNumber: String = "",
    val destinationAccountNumber: String = "",
    val amount: Double = 0.0,
    val timestamp: Long = 0L
)
