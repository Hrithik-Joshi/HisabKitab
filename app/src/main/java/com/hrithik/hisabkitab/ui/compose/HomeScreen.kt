package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme

@Composable
fun HomeScreen()  {
    HisabKitabTheme{
        Scaffold(
            content = { padding ->
                Box(modifier = Modifier.padding(padding).fillMaxSize()){
                    Text(
                        text = "HomeScreen"
                    )
                }
            }
        )
    }
}