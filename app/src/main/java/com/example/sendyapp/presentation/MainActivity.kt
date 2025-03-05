package com.example.sendyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sendyapp.presentation.navigation.Navigation
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import land.sendy.pfe_sdk.api.API

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SendyAppTheme {
                Navigation()
            }
        }
    }
}
