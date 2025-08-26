package com.hrithik.hisabkitab.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hrithik.hisabkitab.R
import com.hrithik.hisabkitab.ui.theme.HisabKitabTheme
import com.hrithik.hisabkitab.ui.theme.interstate_blue_700
import com.hrithik.hisabkitab.ui.theme.interstate_white
import com.hrithik.hisabkitab.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onSignOutClicked: () -> Unit,
    onAddExpenseClicked: () -> Unit,
    onAddIncomeClicked: () -> Unit,
    onAddInvestmentClicked: () -> Unit,
    onAddLoanClicked: () -> Unit
) {

    val showBottomSheet by homeViewModel.showBottomSheet.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val isSignedOut by homeViewModel.isSignedOut.collectAsState()
    val expenseUIState by homeViewModel.expenseUIState.collectAsState()
    val userName = homeViewModel.getUserName()

    if (isSignedOut) {
        onSignOutClicked()
    }

    HisabKitabTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TopAppBarDefaults.topAppBarColors(interstate_blue_700),
                    title = {
                        Text(
                            text = "Hisab Kitab",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.lora_regular)),
                                fontSize = 24.sp,
                                color = interstate_white,
                            ),
                            modifier = Modifier.clickable(
                                onClick = { homeViewModel.showBottomSheet(true) },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { homeViewModel.showBottomSheet(true) }
                        ) {
                            Image(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Settings Icon",
                                modifier = Modifier,
                                colorFilter = ColorFilter.tint(
                                    interstate_white
                                )
                            )
                        }
                    }
                )
            },
            content = { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(interstate_white)
                    ) {
                        // Category Cards Section
                        Row(modifier = Modifier.fillMaxWidth()) {
                            CategoryCard(
                                title = "Expense",
                                gradient = GradientColor.expenseGradient,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                amount = "₹1,200",
                                onClick = onAddExpenseClicked
                            )
                            CategoryCard(
                                title = "Income",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                gradient = GradientColor.incomeGradient,
                                amount = "₹5,000",
                                onClick = onAddIncomeClicked
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            CategoryCard(
                                title = "Loan",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                gradient = GradientColor.loanGradient,
                                amount = "₹15,000",
                                onClick = onAddLoanClicked
                            )
                            CategoryCard(
                                title = "Investment",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp),
                                gradient = GradientColor.investmentGradient,
                                amount = "₹9,000",
                                onClick = onAddInvestmentClicked
                            )
                        }

                        // Recent Transactions Section
                        if (expenseUIState.expenseList.isNotEmpty()) {
                            Text(
                                text = "Recent Transactions",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(16.dp)
                            )

                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(bottom = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(expenseUIState.expenseList) { item ->
                                    ExpenseListItem(item)
                                }
                            }
                        } else {
                            // Empty State
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "📊",
                                    fontSize = 64.sp
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "No transactions yet",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Medium
                                    ),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Start by adding your first expense or income",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = { homeViewModel.showBottomSheet(false) },
                            sheetState = sheetState
                        ) {

                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource(R.drawable.person_icon),
                                    contentDescription = "Settings Icon",
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .size(20.dp),
                                    colorFilter = ColorFilter.tint(
                                        MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                Text(
                                    text = "Account",
                                    modifier = Modifier
                                        .padding(top = 20.dp),
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp)
                                ) {
                                    Image(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Settings Icon",
                                        modifier = Modifier
                                            .padding(end = 12.dp)
                                            .align(Alignment.End)
                                            .clickable(onClick = {
                                                homeViewModel.showBottomSheet(
                                                    false
                                                )
                                            }),
                                        colorFilter = ColorFilter.tint(
                                            MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                }
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painter = painterResource(R.drawable.signout_icon),
                                    contentDescription = "Sign out Icon",
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .size(20.dp),
                                    colorFilter = ColorFilter.tint(
                                        MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                Text(
                                    text = "Sign Out",
                                    modifier = Modifier
                                        .padding(top = 20.dp)
                                        .clickable(onClick = { homeViewModel.signOut() }),
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                )
                            }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (expenseUIState.expenseList.isNotEmpty()) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        val listState = rememberLazyListState()
                                        LazyColumn(
                                            state = listState,
                                            contentPadding = PaddingValues(10.dp),
                                        ) {
                                            items(expenseUIState.expenseList) { item ->
                                                ExpenseListItem(item)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ExpenseListItem(item: HomeViewModel.TransactionData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Category Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = when (item.type.lowercase()) {
                            "expense" -> Color(0xFFFFE4E4)
                            "income" -> Color(0xFFE4F7E4)
                            "loan" -> Color(0xFFE4F4FF)
                            "investment" -> Color(0xFFFFF4E4)
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (item.type) {
                        "General Expense" -> "💸"
                        "Income Expense" -> "💰"
                        "Loan Expense" -> "🏦"
                        "Investment" -> "📈"
                        else -> "💳"
                    },
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Transaction Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Show type prominently at the top
                Text(
                    text = item.type.ifEmpty { "Transaction" },
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = when (item.type.lowercase()) {
                        "expense" -> Color(0xFFD32F2F)
                        "income" -> Color(0xFF388E3C)
                        "loan" -> Color(0xFFFF9800)
                        "investment" -> Color(0xFF1976D2)
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )

                // Show category as secondary info
                if (item.category.isNotEmpty()) {
                    Text(
                        text = item.category,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (item.note.isNotEmpty()) {
                    Text(
                        text = item.note,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (item.subCategory.isNotEmpty()) {
                    Text(
                        text = item.subCategory,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }

            // Amount and Date
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "₹${item.amount}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = when (item.type.lowercase()) {
                        "expense" -> Color(0xFFD32F2F)
                        "income" -> Color(0xFF388E3C)
                        "loan" -> Color(0xFFFF9800)
                        "investment" -> Color(0xFF1976D2)
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )

                Text(
                    text = SimpleDateFormat("MMM dd", Locale.getDefault()).format(item.date.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (item.paymentMode.isNotEmpty()) {
                    Text(
                        text = item.paymentMode,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}