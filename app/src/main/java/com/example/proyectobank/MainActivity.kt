package com.example.proyectobank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectobank.presentation.navigation.AppNavigation
import com.example.proyectobank.presentation.theme.ProyectoBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoBankTheme {
                AppNavigation()
            }
        }
    }
}
