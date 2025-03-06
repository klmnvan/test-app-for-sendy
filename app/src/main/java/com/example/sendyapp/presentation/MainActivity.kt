package com.example.sendyapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import com.example.sendyapp.presentation.components.pullrefresh.CustomPullToRefreshContainer
import com.example.sendyapp.presentation.navigation.Navigation
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import land.sendy.pfe_sdk.api.API

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SendyAppTheme {
                API.getInsatce("https://testwallet.sendy.land/", "sendy")
                Looper.getMainLooper()
                val pullToRefreshState = rememberPullToRefreshState()
                Scaffold(
                    floatingActionButton = {
                        CustomPullToRefreshContainer(pullToRefreshState)
                    }
                ) {
                    Navigation(pullToRefreshState)
                }
            }
        }
    }
}