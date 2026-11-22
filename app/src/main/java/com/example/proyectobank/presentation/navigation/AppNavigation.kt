package com.example.proyectobank.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectobank.presentation.home.ShowHome
import com.example.proyectobank.presentation.login.ShowLogin
import com.example.proyectobank.presentation.register.ShowRegister

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            ShowLogin(navController = navController)
        }
        composable("register") {
            ShowRegister()
        }
        composable("home") {
            ShowHome()
        }
    }
}
