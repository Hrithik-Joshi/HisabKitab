package com.hrithik.hisabkitab.data.repository

import com.hrithik.hisabkitab.data.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransactions(): Flow<List<TransactionEntity>>

    suspend fun insertTransaction(transaction: TransactionEntity)

    suspend fun updateTransaction(transaction: TransactionEntity)

    suspend fun deleteTransaction(transaction: TransactionEntity)
}