package com.hrithik.hisabkitab.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrithik.hisabkitab.data.dataStore.DataStorageManager
import com.hrithik.hisabkitab.data.repository.AuthRepository
import com.hrithik.hisabkitab.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val dataStorageManager: DataStorageManager,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet

    private var _isSignedOut = MutableStateFlow(false)
    val isSignedOut: StateFlow<Boolean> = _isSignedOut

    enum class ExpenseListViewModelState {
        INIT,
        IN_PROGRESS,
        REFRESHING,
        SUCCESS_EXPENSE_LIST,
        ERROR_EXPENSE_LIST,
        EMPTY_EXPENSE_LIST,
    }

    data class TransactionData(
        val amount: String = "",
        val note: String = "",
        val category: String = "",
        val subCategory: String = "",
        val type: String = "",
        val paymentMode: String = "",
        val date: Calendar = Calendar.getInstance()
    )

    data class ExpenseUIState(
        val expenseList: List<TransactionData> = emptyList(),
        val expenseListState: ExpenseListViewModelState = ExpenseListViewModelState.INIT
    )

    private val _expenseUIState = MutableStateFlow(ExpenseUIState())
    val expenseUIState: StateFlow<ExpenseUIState> = _expenseUIState.asStateFlow()

    init {
        fetchTransactions()
    }

    fun showBottomSheet(show: Boolean) {
        Log.d("HomeViewModel", "Setting bottomSheet visibility to: $show")
        _showBottomSheet.value = show
    }

    private fun fetchTransactions() {
        viewModelScope.launch {
            try {
                _expenseUIState.value = _expenseUIState.value.copy(
                    expenseListState = ExpenseListViewModelState.IN_PROGRESS
                )

                // Fetch transactions from repository
                transactionRepository.getAllTransactions().collect { transactions ->
                    if (transactions.isEmpty()) {
                        _expenseUIState.value = _expenseUIState.value.copy(
                            expenseList = emptyList(),
                            expenseListState = ExpenseListViewModelState.EMPTY_EXPENSE_LIST
                        )
                    } else {
                        // Convert repository transactions to UI model
                        val transactionDataList = transactions.map { transaction ->
                            TransactionData(
                                amount = transaction.amount.toString(),
                                note = transaction.note ?: "",
                                category = transaction.category,
                                subCategory = transaction.subCategory,
                                type = transaction.type ?: "",
                                paymentMode = transaction.paymentMode,
                                date = Calendar.getInstance().apply {
                                    try {
                                        // Parse ISO 8601 date string to timestamp
                                        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", java.util.Locale.getDefault())
                                        dateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
                                        val parsedDate = dateFormat.parse(transaction.date)
                                        timeInMillis = parsedDate?.time ?: System.currentTimeMillis()
                                    } catch (e: Exception) {
                                        // Fallback: try to parse as simple date format or use current time
                                        try {
                                            val simpleDateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                                            val parsedDate = simpleDateFormat.parse(transaction.date)
                                            timeInMillis = parsedDate?.time ?: System.currentTimeMillis()
                                        } catch (e2: Exception) {
                                            Log.w("HomeViewModel", "Could not parse date: ${transaction.date}, using current time")
                                            timeInMillis = System.currentTimeMillis()
                                        }
                                    }
                                }
                            )
                        }

                        _expenseUIState.value = _expenseUIState.value.copy(
                            expenseList = transactionDataList,
                            expenseListState = ExpenseListViewModelState.SUCCESS_EXPENSE_LIST
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching transactions", e)
                _expenseUIState.value = _expenseUIState.value.copy(
                    expenseList = emptyList(),
                    expenseListState = ExpenseListViewModelState.ERROR_EXPENSE_LIST
                )
            }
        }
    }

    fun refreshTransactions() {
        fetchTransactions()
    }

    fun signOut() = viewModelScope.launch{
        repository.logout()
        dataStorageManager.clearSession()
        _isSignedOut.value = true
    }

    fun getUserName(): String{
       return repository.currentUser?.displayName.toString()
    }
}