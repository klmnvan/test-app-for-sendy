package com.example.sendyapp.presentation.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme

@Composable
fun ButtonFillWidth(title: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SendyAppTheme.colors.primary,
        )) {

        Text(text = title, style = SendyAppTheme.typography.textButton,
            modifier = Modifier.padding(vertical = 12.dp))
    }
}