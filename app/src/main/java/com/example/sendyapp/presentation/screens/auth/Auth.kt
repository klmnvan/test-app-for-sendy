package com.example.sendyapp.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sendyapp.presentation.components.buttons.ButtonFillWidth
import com.example.sendyapp.presentation.components.spacers.SpacerHeight
import com.example.sendyapp.presentation.components.textfileds.PhoneTextFiled
import com.example.sendyapp.presentation.screens.auth.components.OpenDialogOffer
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth(controller: NavHostController, pullToRefreshState: PullToRefreshState, viewModel: AuthViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()
    var dialogIsOpen by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getOfferText()
    }

    if(dialogIsOpen) {
        if (state.value.textOffer != "") {
            OpenDialogOffer(state.value.textOffer) {
                dialogIsOpen = false
            }
        }
        else {
            Toast.makeText(LocalContext.current, "Подключитесь к интернету и обновите!", Toast.LENGTH_LONG).show()
            dialogIsOpen = false
        }
    }

    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            viewModel.getOfferText()
            delay(1000)
            pullToRefreshState.endRefresh()
        }
    }

    Box(modifier = Modifier
        .background(SendyAppTheme.colors.background)
        .nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        Column (modifier = Modifier
            .fillMaxSize().verticalScroll(rememberScrollState())
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.value.offerIsAgree,
                    onCheckedChange = { viewModel.stateValue = state.value.copy(offerIsAgree = it) },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = SendyAppTheme.colors.container,
                        checkedColor = SendyAppTheme.colors.primary
                    )
                )
                ClickableText(text = buildAnnotatedString {
                    append("Я согласен с ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = SendyAppTheme.colors.primary)) {
                        append("условиями пользователского соглашения")
                    }
                }) {
                    dialogIsOpen = true
                }
            }
            SpacerHeight(12.dp)
            ButtonFillWidth("Продолжить") {
                viewModel.sendCode(controller)
            }
        }
    }

}
