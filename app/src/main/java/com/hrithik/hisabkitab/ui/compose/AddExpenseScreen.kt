package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hrithik.hisabkitab.R
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(onBackClick: () -> Unit) {
    HisabKitabTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    title = {
                        Text(
                            text = "Add Expense",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.lora_regular)),
                                fontSize = 24.sp
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick,modifier = Modifier.testTag("BackNav")) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Navigation",
                            )
                        }
                    },
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {

                }
            }
        )
    }
}

@Preview
@Composable
fun AddExpenseScreenPreview() {
    AddExpenseScreen(
        onBackClick = { /* Handle back click */ }
    )
}