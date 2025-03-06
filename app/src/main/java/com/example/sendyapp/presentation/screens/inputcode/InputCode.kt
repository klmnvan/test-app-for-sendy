package com.example.sendyapp.presentation.screens.inputcode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sendyapp.R
import com.example.sendyapp.presentation.components.buttons.ButtonFillWidth
import com.example.sendyapp.presentation.components.spacers.SpacerHeight
import com.example.sendyapp.presentation.screens.inputcode.components.InputCell
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme

@Composable
fun InputCode(controller: NavHostController, viewModel: InputCodeViewModel = hiltViewModel()) {
    val codeLength = 6
    val code = remember { mutableStateListOf(*Array(codeLength) { "" }) }
    val focusRequesters = List(codeLength) { FocusRequester() }

    Column (modifier = Modifier
        .fillMaxSize()
        .background(SendyAppTheme.colors.background)
        .padding(horizontal = 20.dp, vertical = 60.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(SendyAppTheme.colors.primary)
                .padding(12.dp)
                .size(15.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null) {
                    viewModel.goBack(controller) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_arrow),
                contentDescription = "",
                modifier = Modifier.rotate(-90f),
                tint = SendyAppTheme.colors.background
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center) {
            Text(
                text = "Ввод кода",
                style = SendyAppTheme.typography.titleScreen,
                modifier = Modifier.fillMaxWidth()
            )
            SpacerHeight(40.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                for (i in 0 until codeLength) {
                    InputCell(code[i], focusRequesters[i]) { newValue ->
                        if (newValue.length <= 1) {
                            code[i] = newValue
                            if (newValue.isNotEmpty() && i < codeLength - 1) {
                                focusRequesters[i + 1].requestFocus()
                            } else if (newValue.isEmpty() && i > 0) {
                                focusRequesters[i - 1].requestFocus()
                            }
                        }
                    }
                }
            }
            SpacerHeight(20.dp)
            ButtonFillWidth("Продолжить") {
                viewModel.checkCode(code.joinToString(""))
            }
        }
    }

}