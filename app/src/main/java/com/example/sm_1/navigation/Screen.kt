package com.example.sm_1.navigation

sealed class Screen (val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Recover : Screen("recover")

}