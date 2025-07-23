package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.interstate_white
import com.hrithik.hisabkitab.util.ExpenseCategories.Companion.paymentModes
import com.hrithik.hisabkitab.util.IncomeCategories.Companion.incomeCategoriesWithSubcategories

@Composable
fun AddIncomeScreen(
    onBackClick: () -> Unit
) {
    var isFormValid by remember { mutableStateOf(false) }
    HisabKitabTheme {
        Scaffold(
            topBar = {
                MainTopBar(
                    onBackClick = onBackClick,
                    title = "Add Income",
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(interstate_white)
                ) {
                    AddTransactionContent(
                        categories = incomeCategoriesWithSubcategories,
                        paymentMode = paymentModes, onValidationChange = { isFormValid = it })
                }

            },
            bottomBar = {
                MainBottomBar(
                    isEnabled = isFormValid,
                    onSaveClick = { /* handle save */ })
            }
        )
    }
}
