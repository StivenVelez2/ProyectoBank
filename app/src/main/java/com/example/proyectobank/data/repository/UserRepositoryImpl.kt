package com.example.proyectobank.data.repository

import com.example.proyectobank.data.mapper.toUser
import com.example.proyectobank.domain.model.User
import com.example.proyectobank.domain.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class UserRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : UserRepository {

    private val usersCollection = firestore.collection("users")

    override fun getUser(
        uid: String,
        onSuccess: (User?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        usersCollection.document(uid).get()
            .addOnSuccessListener { document ->
                onSuccess(document.toUser())
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}
