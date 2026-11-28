package com.example.proyectobank.data.auth

import android.content.Context
import com.example.proyectobank.data.mapper.toUser
import com.example.proyectobank.data.security.PasswordHasher
import com.example.proyectobank.domain.exception.AuthException
import com.example.proyectobank.domain.model.User
import com.example.proyectobank.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FirestoreAuthRepositoryImpl(
    context: Context,
    private val firestore: FirebaseFirestore = Firebase.firestore
) : AuthRepository {

    private val usersCollection = firestore.collection("users")
    private val preferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    override fun currentUserId(): String? = preferences.getString("uid", null)

    override fun register(
        user: User,
        password: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        usersCollection
            .whereEqualTo("documentNumber", user.documentNumber)
            .limit(1)
            .get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.isEmpty) {
                    onFailure(AuthException("Ese documento ya está registrado"))
                    return@addOnSuccessListener
                }

                val document = usersCollection.document()
                val data = hashMapOf<String, Any>(
                    "documentNumber" to user.documentNumber,
                    "firstName" to user.firstName,
                    "lastName" to user.lastName,
                    "email" to user.email,
                    "phoneNumber" to user.phoneNumber,
                    "passwordHash" to PasswordHasher.hash(password)
                )

                document.set(data)
                    .addOnSuccessListener {
                        preferences.edit().putString("uid", document.id).apply()
                        onSuccess(document.id)
                    }
                    .addOnFailureListener { exception ->
                        onFailure(exception)
                    }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    override fun login(
        documentNumber: String,
        password: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        usersCollection
            .whereEqualTo("documentNumber", documentNumber)
            .limit(1)
            .get()
            .addOnSuccessListener { snapshot ->
                val document = snapshot.documents.firstOrNull()
                val storedHash = document?.getString("passwordHash")

                if (document == null || storedHash == null || storedHash != PasswordHasher.hash(password)) {
                    onFailure(AuthException("Documento o contraseña incorrectos"))
                    return@addOnSuccessListener
                }

                val user = document.toUser()
                if (user == null) {
                    onFailure(AuthException("No se pudo leer el usuario"))
                    return@addOnSuccessListener
                }

                preferences.edit().putString("uid", document.id).apply()
                onSuccess(user)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    override fun logout() {
        preferences.edit().remove("uid").apply()
    }
}
