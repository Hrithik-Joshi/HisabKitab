package com.hrithik.hisabkitab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hrithik.hisabkitab.ui.compose.HomeScreen
import com.hrithik.hisabkitab.ui.compose.LoginScreen
import com.hrithik.hisabkitab.ui.compose.SignUpScreen

@Composable
fun HisabKitabNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Login.route
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(NavigationItem.SignUp.route)
                },
                onLoginSuccess = {
                    navController.navigate(NavigationItem.Home.route) {
                        // Clear the back stack to prevent going back to login
                        popUpTo(NavigationItem.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(NavigationItem.SignUp.route) {
            SignUpScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onAlreadyHaveAccountClick = {
                    navController.popBackStack()
                },
                onSignUpSuccess = {
                    navController.navigate(NavigationItem.Login.route) {
                        // Clear the back stack to prevent going back to signup
                        popUpTo(NavigationItem.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
    }
}
