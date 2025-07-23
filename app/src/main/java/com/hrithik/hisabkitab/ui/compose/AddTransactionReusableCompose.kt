package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrithik.hisabkitab.R
import com.hrithik.hisabkitab.ui.theme.interstate_blue_600
import com.hrithik.hisabkitab.ui.theme.interstate_blue_700
import com.hrithik.hisabkitab.ui.theme.interstate_white
import com.hrithik.hisabkitab.ui.theme.text_grey
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    onBackClick: () -> Unit,
    title: String
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(interstate_blue_600),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.lora_regular)),
                    fontSize = 24.sp,
                    color = interstate_white,
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick, modifier = Modifier.testTag("BackNav")) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Navigation",
                    tint = interstate_white
                )
            }
        },
    )
}

@Composable
fun MainBottomBar(isEnabled: Boolean = true, onSaveClick: () -> Unit = {}){
    Box(modifier = Modifier.background(interstate_white)) {
        HorizontalDivider()
        Button(
            onClick = onSaveClick,
            enabled = isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled) interstate_blue_700 else Color.Gray,
                disabledContainerColor = Color.Gray
            ),
        ) {
            Text(
                text = "Save Transaction",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                color = if (isEnabled) interstate_white else Color.White.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun AddTransactionContent(categories: Map<String, List<String>>, paymentMode: List<String>, onValidationChange: (Boolean) -> Unit = {}) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedSubCategory by remember { mutableStateOf("") }
    var selectedPaymentMode by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    // Validation logic - check if required fields are filled
    val isFormValid = amount.isNotBlank() &&
            selectedCategory.isNotBlank() &&
            selectedPaymentMode.isNotBlank()

    // Notify parent about validation state changes
    remember(isFormValid) {
        onValidationChange(isFormValid)
        isFormValid
    }

    val expenseCategories = categories.keys.toList()
    val currentSubCategories = if (selectedCategory.isNotEmpty()) {
        categories[selectedCategory] ?: emptyList()
    } else {
        emptyList()
    }

    // Reset subcategory when main category changes
    if (selectedCategory.isNotEmpty() && !currentSubCategories.contains(selectedSubCategory)) {
        selectedSubCategory = ""
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AddExpenseAmountCard(
                amount = amount,
                onAmountChange = { amount = it }
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.padding(8.dp)) {
                CategoryDropdown(
                    label = "Category",
                    items = expenseCategories,
                    selectedItem = selectedCategory,
                    onItemSelected = { selectedCategory = it }
                )
            }
            Row(modifier = Modifier.padding(8.dp)) {
                SubCategoryDropdown(
                    label = "Subcategory",
                    items = currentSubCategories,
                    selectedItem = selectedSubCategory,
                    onItemSelected = { selectedSubCategory = it },
                    enabled = selectedCategory.isNotEmpty()
                )
            }
            Row(modifier = Modifier.padding(8.dp)) {
                CategoryDropdown(
                    label = "Payment Mode",
                    items = paymentMode,
                    selectedItem = selectedPaymentMode,
                    onItemSelected = { selectedPaymentMode = it }
                )
            }
            Row(modifier = Modifier.padding(8.dp)) {
                DateSelectionField(
                    selectedDate = selectedDate,
                    onDateSelected = { selectedDate = it }
                )
            }
            Row(modifier = Modifier.padding(8.dp)) {
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Note",
                        color = text_grey) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Normal,
                        color = interstate_blue_700
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.outline,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = {
                        Text(
                            text = "Add any additional notes here",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    label,
                    color = text_grey
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                color = interstate_blue_700
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),

            colors = OutlinedTextFieldDefaults.colors(interstate_blue_700)


        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = interstate_white
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            selectionOption,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        onItemSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCategoryDropdown(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },

        ) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    label,
                    color = text_grey
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                color = interstate_blue_700
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),

            colors = OutlinedTextFieldDefaults.colors(interstate_blue_700)


        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = interstate_white
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            selectionOption,
                            color = Color.Black
                        )
                    },
                    onClick = {
                        onItemSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionField(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val dateFormatter = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate.timeInMillis
    )

    OutlinedTextField(
        value = dateFormatter.format(selectedDate.time),
        onValueChange = { },
        readOnly = true,
        label = { Text("Date") },
        trailingIcon = {
            IconButton(onClick = { showDatePicker = true }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select date"
                )
            }
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Normal,
            color = interstate_blue_700
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(interstate_blue_700)
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val newDate = Calendar.getInstance().apply {
                            timeInMillis = millis
                        }
                        onDateSelected(newDate)
                    }
                    showDatePicker = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
