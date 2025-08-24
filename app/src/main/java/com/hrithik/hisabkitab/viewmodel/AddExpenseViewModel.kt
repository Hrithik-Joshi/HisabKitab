package com.hrithik.hisabkitab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.hrithik.hisabkitab.data.dao.TransactionDao
import com.hrithik.hisabkitab.data.entity.TransactionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val transactionDao: TransactionDao
) : ViewModel() {
    data class TransactionData(
        val amount: String = "",
        val note: String = "",
        val category: String = "",
        val subCategory: String = "",
        val paymentMode: String = "",
        val date: Calendar = Calendar.getInstance()
    )

    data class TransactionUIState(
        val transactionData: TransactionData = TransactionData()
    )

    private val _transactionUIState = MutableStateFlow(TransactionUIState())
    val transactionUIState: StateFlow<TransactionUIState> = _transactionUIState.asStateFlow()

    fun saveExpense(type: String?) {
        val data = _transactionUIState.value.transactionData
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val entity = TransactionEntity(
            type = type,
            category = data.category,
            subCategory = data.subCategory,
            amount = data.amount.toDoubleOrNull() ?: 0.0,
            date = dateFormat.format(data.date.time),
            note = data.note
        )
        viewModelScope.launch {
            transactionDao.saveTransaction(entity)
        }
    }

    fun updateAmount(amount: String) {
        _transactionUIState.value = _transactionUIState.value.copy(
            transactionData = _transactionUIState.value.transactionData.copy(amount = amount)
        )
    }

    fun updateNote(note: String) {
        _transactionUIState.value = _transactionUIState.value.copy(
            transactionData = _transactionUIState.value.transactionData.copy(note = note)
        )
    }

    fun updateCategory(category: String) {
        _transactionUIState.value = _transactionUIState.value.copy(
            transactionData = _transactionUIState.value.transactionData.copy(category = category, subCategory = "")
        )
    }

    fun updateSubCategory(subCategory: String) {
        _transactionUIState.value = _transactionUIState.value.copy(
            transactionData = _transactionUIState.value.transactionData.copy(subCategory = subCategory)
        )
    }

    fun updatePaymentMode(paymentMode: String) {
        _transactionUIState.value = _transactionUIState.value.copy(
            transactionData = _transactionUIState.value.transactionData.copy(paymentMode = paymentMode)
        )
    }

    fun updateDate(date: Calendar) {
        _transactionUIState.value = _transactionUIState.value.copy(
            transactionData = _transactionUIState.value.transactionData.copy(date = date)
        )
    }
}