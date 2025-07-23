package com.hrithik.hisabkitab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hrithik.hisabkitab.ui.compose.AddExpenseScreen
import com.hrithik.hisabkitab.ui.compose.AddIncomeScreen
import com.hrithik.hisabkitab.ui.compose.AddInvestmentScreen
import com.hrithik.hisabkitab.ui.compose.AddLoanScreen
import com.hrithik.hisabkitab.ui.compose.HomeScreen
import com.hrithik.hisabkitab.ui.compose.LoginScreen
import com.hrithik.hisabkitab.ui.compose.SignUpScreen

@Composable
fun HisabKitabNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
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
            HomeScreen(
                onSignOutClicked = {
                    navController.navigate(NavigationItem.Login.route) {
                        popUpTo(NavigationItem.Home.route) {
                            inclusive = true
                        }
                    }
                },
                onAddExpenseClicked = {
                    navController.navigate(NavigationItem.AddExpense.route)
                },
                onAddIncomeClicked = {
                    navController.navigate(NavigationItem.AddIncome.route)
                },
                onAddInvestmentClicked = {
                    navController.navigate(NavigationItem.AddInvestment.route)
                },
                onAddLoanClicked = {
                    navController.navigate(NavigationItem.AddLoan.route)
                }
            )
        }


        composable(NavigationItem.AddExpense.route) {
            AddExpenseScreen(onBackClick = {
                navController.navigate(
                    NavigationItem.Home.route
                )
            })
        }

        composable(NavigationItem.AddIncome.route) {
            AddIncomeScreen(onBackClick = {
                navController.navigate(
                    NavigationItem.Home.route
                )
            })
        }

        composable(NavigationItem.AddInvestment.route) {
            AddInvestmentScreen(onBackClick = {
                navController.navigate(
                    NavigationItem.Home.route
                )
            })
        }

        composable(NavigationItem.AddLoan.route) {
            AddLoanScreen(onBackClick = {
                navController.navigate(
                    NavigationItem.Home.route
                )
            })
        }
    }

}
