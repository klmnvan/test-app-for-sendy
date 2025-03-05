package com.example.sendyapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sendyapp.R
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme

@Composable
fun Splash(controller: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.launch(controller)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SendyAppTheme.colors.background)
            .padding(horizontal = 50.dp),
        contentAlignment = Alignment.Center) {

        Image(imageVector = ImageVector.vectorResource(R.drawable.logo), contentDescription = null,
            modifier = Modifier.fillMaxWidth())

    }

}

