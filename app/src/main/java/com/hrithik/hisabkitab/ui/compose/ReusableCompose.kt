package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hrithik.hisabkitab.ui.compose.GradientColor.Companion.expenseAdd
import com.hrithik.hisabkitab.ui.theme.text_charcoal
import com.hrithik.hisabkitab.ui.theme.text_grey
import com.hrithik.hisabkitab.viewmodel.AddExpenseViewModel

@Composable
fun ErrorDialog(
    title: String = "Error",
    message: String = "An error occurred",
    onDismiss: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        onClick = { }  // Intercept clicks to prevent them from passing through
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("OK")
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true
            )
        )
    }
}

@Composable
fun CategoryCard(
    title: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    amount: String? = null,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(16.dp)
        ) {
            Row(
            ) {
                amount?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = text_charcoal,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.Black
                )
                Image(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = "Settings Icon"
                )

            }
        }
    }
}

@Composable
fun AddExpenseAmountCard(
    amount: String,
    onAmountChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(expenseAdd),

        ) {
            Text(
                text = "Enter Amount",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = text_grey
                ),
                modifier = Modifier.padding(12.dp).align(Alignment.CenterHorizontally),

            )

            OutlinedTextField(
                value = amount,
                onValueChange = onAmountChange,
                label = {
                    Text(
                        text = "₹ Amount",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = text_grey,
                            fontWeight = FontWeight.Light
                        ),
                    )
                },
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .align(Alignment.CenterHorizontally),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

        }
    }
}

@Composable
fun SaveExpenseView(
    saveExpenseViewModelState: AddExpenseViewModel.SaveExpenseModelState,
    onSaved: () -> Unit
) {
    when(saveExpenseViewModelState){
        AddExpenseViewModel.SaveExpenseModelState.COMPLETED -> {
            onSaved()
        }
        AddExpenseViewModel.SaveExpenseModelState.IN_PROGRESS -> {
            NonDismissableDialog()
        }
        AddExpenseViewModel.SaveExpenseModelState.ERROR -> {
            ErrorDialog(
                title = "Error",
                message = "Failed to save expense. Please try again.",
                onDismiss = onSaved
            )
        }
        else -> {}
    }
}

@Composable
fun NonDismissableDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}