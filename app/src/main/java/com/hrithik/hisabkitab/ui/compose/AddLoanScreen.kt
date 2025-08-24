package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.interstate_white
import com.hrithik.hisabkitab.util.ExpenseCategories.Companion.paymentModes
import com.hrithik.hisabkitab.util.LoanCategories.Companion.loanCategoriesWithSubcategories
import com.hrithik.hisabkitab.viewmodel.AddExpenseViewModel

@Composable
fun AddLoanScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AddExpenseViewModel = hiltViewModel()
    val uiState by viewModel.transactionUIState.collectAsState()
    val data = uiState.transactionData
    var isFormValid by remember { mutableStateOf(false) }
    HisabKitabTheme {
        Scaffold(
            topBar = {
                MainTopBar(
                    onBackClick = onBackClick,
                    title = "Add Loan",
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
                        categories = loanCategoriesWithSubcategories,
                        paymentMode = paymentModes, onValidationChange = { isFormValid = it },
                        updateAmount = { viewModel.updateAmount(it) },
                        updateCategory = { viewModel.updateCategory(it) },
                        updateSubCategory = { viewModel.updateSubCategory(it) },
                        updatePaymentMode = { viewModel.updatePaymentMode(it) },
                        updateDate = { viewModel.updateDate(it) },
                        updateNote = { viewModel.updateNote(it) },
                        data = data
                    )
                }

            },
            bottomBar = {
                MainBottomBar(
                    isEnabled = isFormValid,
                    onSaveClick = { viewModel.saveExpense(type = "Loan Expense") })
            }
        )
    }
}