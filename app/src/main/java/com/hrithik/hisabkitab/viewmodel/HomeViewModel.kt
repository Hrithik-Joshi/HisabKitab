package com.hrithik.hisabkitab.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hrithik.hisabkitab.data.dataStore.DataStorageManager
import com.hrithik.hisabkitab.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val dataStorageManager: DataStorageManager
) : ViewModel() {

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet

    private var _isSignedOut = MutableStateFlow(false)
    val isSignedOut: StateFlow<Boolean> = _isSignedOut

    fun showBottomSheet(show: Boolean) {
        Log.d("HomeViewModel", "Setting bottomSheet visibility to: $show")
        _showBottomSheet.value = show
    }

    fun signOut() = viewModelScope.launch{
        repository.logout()
        dataStorageManager.clearSession()
        _isSignedOut.value = true
    }
}