package com.hrithik.hisabkitab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hrithik.hisabkitab.data.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM TransactionTable ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

//    @Query("SELECT * FROM TransactionTable WHERE type = :type ORDER BY date DESC")
//    fun getTransactionsByMonth(type: String): Flow<List<TransactionEntity>>

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

}