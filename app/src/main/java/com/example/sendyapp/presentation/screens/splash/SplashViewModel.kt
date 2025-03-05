package com.example.sendyapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.sendyapp.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    fun launch(navController: NavHostController) {
        viewModelScope.launch {
            delay(2000L)
            navController.navigate(NavigationRoutes.AUTH) {
                popUpTo(NavigationRoutes.SPLASH) {
                    inclusive = true
                }
            }
        }
    }


}

