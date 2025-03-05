package com.example.sendyapp.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sendyapp.presentation.components.buttons.ButtonFillWidth
import com.example.sendyapp.presentation.components.spacers.SpacerHeight
import com.example.sendyapp.presentation.components.textfileds.PhoneTextFiled
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme
import kotlin.math.absoluteValue

@Composable
fun Auth(controller: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Column (modifier = Modifier
        .fillMaxSize()
        .background(SendyAppTheme.colors.background)
        .padding(horizontal = 20.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Авторизация", style = SendyAppTheme.typography.titleScreen, modifier = Modifier.fillMaxWidth())
        SpacerHeight(40.dp)
        Text("Номер телефона", style = SendyAppTheme.typography.tittleField)
        SpacerHeight(12.dp)
        PhoneTextFiled(state.value.phoneNumber, "+7 ХХХ ХХХ ХХ ХХ") {
            viewModel.stateValue = state.value.copy(phoneNumber = it)
        }
        SpacerHeight(12.dp)
        ButtonFillWidth("Продолжить") {

        }
    }

}
