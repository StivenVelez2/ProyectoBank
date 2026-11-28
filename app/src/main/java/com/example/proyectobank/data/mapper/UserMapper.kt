package com.example.proyectobank.data.mapper

import com.example.proyectobank.domain.model.User
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toUser(): User? {
    if (!exists()) return null

    return User(
        uid = id,
        documentNumber = getString("documentNumber") ?: "",
        firstName = getString("firstName") ?: "",
        lastName = getString("lastName") ?: "",
        email = getString("email") ?: "",
        phoneNumber = getString("phoneNumber") ?: ""
    )
}
