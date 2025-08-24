package com.hrithik.hisabkitab.di

import android.app.Application
import androidx.room.Room
import com.hrithik.hisabkitab.data.TransactionDatabase
import com.hrithik.hisabkitab.data.dao.TransactionDao
import com.hrithik.hisabkitab.data.repository.TransactionRepository
import com.hrithik.hisabkitab.data.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): TransactionDatabase {
        return Room.databaseBuilder(
            app,
            TransactionDatabase::class.java,
            "TransactionDatabase"
        ).build()
    }

    @Provides
    fun provideMyRepository(myDb: TransactionDatabase): TransactionRepository {
        return TransactionRepositoryImpl(myDb.transactionDao())
    }

    @Provides
    fun provideTransactionDao(db: TransactionDatabase): TransactionDao {
        return db.transactionDao()
    }
}