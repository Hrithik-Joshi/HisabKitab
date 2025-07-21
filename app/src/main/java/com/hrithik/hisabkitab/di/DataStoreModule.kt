package com.hrithik.hisabkitab.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.hrithik.hisabkitab.data.dataStore.DataStorageManager
import com.hrithik.hisabkitab.data.dataStore.DataStorageManagerImpl
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EncryptedStorage

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UnencryptedStorage

private val Context.encryptedDataStore by preferencesDataStore(name = "encrypted_preferences")
private val Context.unencryptedDataStore by preferencesDataStore(name = "unencrypted_preferences")

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindDataStorageManager(
        dataStorageManagerImpl: DataStorageManagerImpl
    ): DataStorageManager

    companion object {
        @Provides
        @Singleton
        @EncryptedStorage
        @OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
        fun provideEncryptedFlowSettings(
            @ApplicationContext context: Context
        ): FlowSettings {
            return DataStoreSettings(context.encryptedDataStore)
        }

        @Provides
        @Singleton
        @UnencryptedStorage
        @OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
        fun provideUnencryptedFlowSettings(
            @ApplicationContext context: Context
        ): FlowSettings {
            return DataStoreSettings(context.unencryptedDataStore)
        }
    }
}
