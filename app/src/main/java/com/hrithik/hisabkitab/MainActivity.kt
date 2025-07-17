package com.hrithik.hisabkitab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.hrithik.hisabkitab.navigation.HisabKitabNavigation
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HisabKitabTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HisabKitabNavigation()
                }
            }
        }
    }
}