package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.interstate_blue_600
import com.hrithik.hisabkitab.ui.theme.interstate_blue_700
import com.hrithik.hisabkitab.ui.theme.interstate_white
import com.hrithik.hisabkitab.ui.theme.text_grey
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(onBackClick: () -> Unit) {

    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedSubCategory by remember { mutableStateOf("") }
    var selectedPaymentMode by remember { mutableStateOf("") }

    val categoriesWithSubcategories = mapOf(
        "Food & Dining" to listOf("Groceries", "Restaurants", "Coffee & Snacks", "Fast Food", "Delivery"),
        "Transportation" to listOf("Fuel", "Cab / Taxi", "Public Transport", "Parking", "Vehicle Maintenance"),
        "Housing" to listOf("Rent", "Utilities (Electricity, Water, Gas)", "Internet", "Repairs & Maintenance"),
        "Shopping" to listOf("Clothing", "Electronics", "Home Supplies", "Personal Care", "Gifts"),
        "Health & Medical" to listOf("Doctor Visits", "Medicines", "Health Insurance", "Gym / Fitness"),
        "Entertainment" to listOf("Movies / OTT", "Events / Concerts", "Games", "Subscriptions (Netflix, Spotify, etc.)"),
        "Education" to listOf("Tuition", "Books", "Courses (Online/Offline)"),
        "Bills & EMI" to listOf("Credit Card Payment", "Loan EMI", "Mobile Recharge / Bill"),
        "Travel" to listOf("Hotel", "Flights / Train / Bus", "Tourism Activities", "Travel Insurance"),
        "Family & Kids" to listOf("Childcare", "School Fees", "Toys", "Elder Care"),
        "Pets" to listOf("Food", "Vet", "Grooming"),
        "Other" to listOf("Other")
    )

    val expenseCategories = categoriesWithSubcategories.keys.toList()

    val currentSubCategories = if (selectedCategory.isNotEmpty()) {
        categoriesWithSubcategories[selectedCategory] ?: emptyList()
    } else {
        emptyList()
    }

    val paymentModes = listOf(
        "Cash", "UPI", "Debit Card", "Credit Card", "Net Banking", "Other"
    )

    // Reset subcategory when main category changes
    if (selectedCategory.isNotEmpty() && !currentSubCategories.contains(selectedSubCategory)) {
        selectedSubCategory = ""
    }

    val focusManager = LocalFocusManager.current

    HisabKitabTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TopAppBarDefaults.topAppBarColors(interstate_blue_600),
                    title = {
                        Text(
                            text = "Add Expense",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.lora_regular)),
                                fontSize = 24.sp,
                                color = interstate_white,
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick, modifier = Modifier.testTag("BackNav"),) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Navigation",
                                tint = interstate_white
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
                        .background(interstate_white)
                ) {
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
                        Column( modifier = Modifier.fillMaxSize()) {
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
                                    items = paymentModes,
                                    selectedItem = selectedPaymentMode,
                                    onItemSelected = { selectedPaymentMode = it }
                                )
                            }
                            Row(modifier = Modifier.padding(8.dp)) {
                                OutlinedTextField(
                                    value = note,
                                    onValueChange = { note = it },
                                    label = { Text("Note") },
                                    modifier = Modifier.fillMaxWidth()
                                        .height(100.dp),
                                    keyboardOptions = KeyboardOptions(
                                        capitalization = KeyboardCapitalization.Sentences,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(onDone = {
                                        focusManager.clearFocus()
                                    }),
                                    textStyle = MaterialTheme.typography.bodyMedium,
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
                                            modifier = Modifier.testTag("NotesPlaceholder")
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                Box(modifier = Modifier.background(interstate_white)) {
                    HorizontalDivider()
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                            .height(45.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = interstate_blue_700),
                    ) {
                        Text(
                            text = "Save Expense",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            ),
                            color = interstate_white
                        )
                    }
                }
            }
        )
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
            label = { Text(label,
                color = text_grey)  },
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
            shape = RoundedCornerShape(12.dp,),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor( MenuAnchorType.PrimaryNotEditable, true),

            colors = OutlinedTextFieldDefaults.colors(interstate_blue_700)


        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = interstate_white
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption,
                        color = Color.Black) },
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
            label = { Text(label,
                color = text_grey)  },
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
            shape = RoundedCornerShape(12.dp,),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor( MenuAnchorType.PrimaryNotEditable, true),

            colors = OutlinedTextFieldDefaults.colors(interstate_blue_700)


        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = interstate_white
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption,
                        color = Color.Black) },
                    onClick = {
                        onItemSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}
