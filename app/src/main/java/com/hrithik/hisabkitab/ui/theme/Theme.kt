package com.hrithik.hisabkitab.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    surface = Purple80.copy(alpha = 0.2f),
    onSurface = Purple80,
    inverseSurface = Purple80,
    inverseOnSurface = Purple80.copy(alpha = 0.2f),
    surfaceVariant = Purple80.copy(alpha = 0.3f),
    onSurfaceVariant = Purple80
)

val LightColorScheme = lightColorScheme(
    primary = interstate_blue_700,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = interstate_white,
    surface = interstate_white,
    onPrimary = interstate_white,
    onSecondary = text_charcoal,
    onTertiary = text_charcoal,
    onBackground = text_charcoal,
    onSurface = text_charcoal
)

@Composable
fun HisabKitabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = LightColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}