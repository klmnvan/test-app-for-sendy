package com.example.sendyapp.presentation.screens.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sendyapp.data.states.AuthSt
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _state = MutableStateFlow(AuthSt())
    val state: StateFlow<AuthSt> = _state.asStateFlow()

    var stateValue: AuthSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

}

