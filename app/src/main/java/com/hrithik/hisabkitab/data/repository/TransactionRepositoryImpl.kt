package com.hrithik.hisabkitab.data.repository

import com.hrithik.hisabkitab.data.dao.TransactionDao
import com.hrithik.hisabkitab.data.entity.TransactionEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
): TransactionRepository {

    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return dao.getAllTransactions()
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        dao.saveTransaction(transaction)
    }

    override suspend fun updateTransaction(transaction: TransactionEntity) {
        dao.updateTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        dao.deleteTransaction(transaction)
    }


}