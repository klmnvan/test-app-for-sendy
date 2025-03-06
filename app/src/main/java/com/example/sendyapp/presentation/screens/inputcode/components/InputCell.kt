package com.example.sendyapp.presentation.screens.inputcode.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme

@Composable
fun InputCell(value: String, focusRequester: FocusRequester, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.filter { it.isDigit() })},
        singleLine = true,
        maxLines = 1, shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(46.dp)
            .height(99.dp)
            .focusRequester(focusRequester),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = SendyAppTheme.colors.primary,
            unfocusedContainerColor = SendyAppTheme.colors.background,
            unfocusedTextColor = SendyAppTheme.colors.textTittle,
            focusedContainerColor = SendyAppTheme.colors.background,
            focusedTextColor = SendyAppTheme.colors.textTittle,
            focusedBorderColor = SendyAppTheme.colors.primary
        ),
        textStyle = SendyAppTheme.typography.textInField,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}