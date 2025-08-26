package com.hrithik.hisabkitab.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hrithik.hisabkitab.data.dao.TransactionDao
import com.hrithik.hisabkitab.data.entity.TransactionEntity


@Database(
    entities = [TransactionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}