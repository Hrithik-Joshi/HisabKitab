package com.hrithik.hisabkitab.navigation

/**
 * Navigation routes for the HisabKitab app
 */
sealed class NavigationItem(val route: String) {
    object Login : NavigationItem("login")
    object SignUp : NavigationItem("signup")
    object Home : NavigationItem("home")
}
