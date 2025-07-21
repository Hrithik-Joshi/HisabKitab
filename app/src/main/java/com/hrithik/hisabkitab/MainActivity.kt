package com.hrithik.hisabkitab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.hrithik.hisabkitab.navigation.HisabKitabNavigation
import com.hrithik.hisabkitab.navigation.NavigationItem
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            val isLoggedIn = authViewModel.checkSession()

            setContent {
                HisabKitabTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        HisabKitabNavigation(
                            startDestination = if (isLoggedIn) NavigationItem.Home.route else NavigationItem.Login.route
                        )
                    }
                }
            }
        }
    }
}