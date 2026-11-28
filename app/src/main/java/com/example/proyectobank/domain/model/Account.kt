package com.example.proyectobank.domain.model

data class Account(
    val accountNumber: String = "",
    val ownerDocumentNumber: String = "",
    val balance: Double = 0.0
)
