package com.example.sendyapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sendyapp.presentation.screens.auth.Auth
import com.example.sendyapp.presentation.screens.inputcode.InputCode
import com.example.sendyapp.presentation.screens.splash.Splash
import land.sendy.pfe_sdk.api.API

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(pullToRefreshState: PullToRefreshState = rememberPullToRefreshState()) {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = NavigationRoutes.SPLASH) {

        composable(NavigationRoutes.SPLASH) {
            Splash(controller)
        }

        composable(NavigationRoutes.AUTH) {
            Auth(controller, pullToRefreshState)
        }

        composable(NavigationRoutes.INPUT_CODE) {
            InputCode(controller)
        }

    }
}