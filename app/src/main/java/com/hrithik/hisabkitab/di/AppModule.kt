package com.hrithik.hisabkitab.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.hrithik.hisabkitab.data.TransactionDatabase
import com.hrithik.hisabkitab.data.repository.AuthRepository
import com.hrithik.hisabkitab.data.repository.AuthRepositoryImpl
import com.hrithik.hisabkitab.data.repository.TransactionRepository
import com.hrithik.hisabkitab.data.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): TransactionDatabase {
        return Room.databaseBuilder(
            app,
            TransactionDatabase::class.java,
            "TransactionDatabase"
        )
            .build()
    }

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideMyRepository(mydb:TransactionDatabase) : TransactionRepository{
        return TransactionRepositoryImpl(mydb.dao)
    }
}
