package com.hrithik.hisabkitab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.lifecycleScope
import com.hrithik.hisabkitab.navigation.HisabKitabNavigation
import com.hrithik.hisabkitab.navigation.NavigationItem
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.LightColorScheme
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
                    Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)) {
                        HisabKitabNavigation(
                            startDestination = if (isLoggedIn) NavigationItem.Home.route else NavigationItem.Login.route
                        )
                    }
                    StatusBarProtection()
                }
            }
        }
    }
}
@Composable
private fun StatusBarProtection(
    heightProvider: () -> Float = calculateGradientHeight(),
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(with(LocalDensity.current) { heightProvider().toDp() })
    ) {
        drawRect(
            color = LightColorScheme.primary,
            size = Size(size.width, size.height)
        )
    }
}

@Composable
fun calculateGradientHeight(): () -> Float {
    val statusBars = WindowInsets.statusBars
    val density = LocalDensity.current
    return { statusBars.getTop(density).times(1.2f) }
}