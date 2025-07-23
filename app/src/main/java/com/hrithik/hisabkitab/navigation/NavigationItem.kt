package com.hrithik.hisabkitab.navigation

/**
 * Navigation routes for the HisabKitab app
 */
sealed class NavigationItem(val route: String) {
    object Login : NavigationItem("login")
    object SignUp : NavigationItem("signup")
    object Home : NavigationItem("home")

    object AddExpense : NavigationItem("addExpense")
    object AddIncome : NavigationItem("addIncome")
    object AddInvestment : NavigationItem("addInvestment")
    object AddLoan : NavigationItem("addLoan")

}
