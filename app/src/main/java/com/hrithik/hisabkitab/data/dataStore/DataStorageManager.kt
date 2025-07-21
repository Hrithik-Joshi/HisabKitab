package com.hrithik.hisabkitab.data.dataStore

import kotlinx.coroutines.flow.Flow

interface DataStorageManager {
    suspend fun saveUserSession(userId: String, email: String)
    suspend fun clearSession()
    suspend fun isLoggedIn(): Boolean
}
