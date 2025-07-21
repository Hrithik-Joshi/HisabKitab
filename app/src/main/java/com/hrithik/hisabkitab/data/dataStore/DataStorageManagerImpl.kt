package com.hrithik.hisabkitab.data.dataStore

import com.hrithik.hisabkitab.di.EncryptedStorage
import com.hrithik.hisabkitab.di.UnencryptedStorage
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalSettingsApi::class)
class DataStorageManagerImpl @Inject constructor(
    @EncryptedStorage private val encryptedStorage: FlowSettings,
    @UnencryptedStorage private val unencryptedStorage: FlowSettings
) : DataStorageManager {

    companion object {
        private const val KEY_USER_ID = "key_user_id"
        private const val KEY_USER_EMAIL = "key_user_email"
        private const val KEY_IS_LOGGED_IN = "key_is_logged_in"
    }

    override suspend fun saveUserSession(userId: String, email: String) {
        encryptedStorage.putString(KEY_USER_ID, userId)
        encryptedStorage.putString(KEY_USER_EMAIL, email)
        unencryptedStorage.putBoolean(KEY_IS_LOGGED_IN, true)
    }

    override suspend fun clearSession() {
        encryptedStorage.remove(KEY_USER_ID)
        encryptedStorage.remove(KEY_USER_EMAIL)
        unencryptedStorage.putBoolean(KEY_IS_LOGGED_IN, false)
    }

    override suspend fun isLoggedIn(): Boolean {
        return unencryptedStorage.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    suspend fun getUserId() =encryptedStorage.getStringOrNull(KEY_USER_ID)

    suspend fun getUserEmail() = encryptedStorage.getStringOrNull(KEY_USER_EMAIL)
}
