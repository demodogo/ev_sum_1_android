package com.example.sm_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sm_1.ui.login.LoginScreen
import com.example.sm_1.ui.recover.RecoverScreen
import com.example.sm_1.ui.register.RegisterScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(
                onRegisterClick = { navController.navigate(Screen.Register.route) },
                onRecoverClick = { navController.navigate(Screen.Recover.route) },
                onLoginClick = {  }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen {
                navController.popBackStack()
            }
        }

        composable(Screen.Recover.route) {
            RecoverScreen {
                navController.popBackStack()
            }
        }
    }
}
