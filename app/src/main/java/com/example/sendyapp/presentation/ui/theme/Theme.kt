package com.example.sendyapp.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable


object SendyAppTheme {
    val typography: Typography
        @ReadOnlyComposable
        @Composable
        get() = LocalTypography.current
    val colors: ColorPalette
        @ReadOnlyComposable
        @Composable
        get() = LocalColors.current
}

@Composable
fun SendyAppTheme(
    typography: Typography = SendyAppTheme.typography,
    content: @Composable () -> Unit
) {
    val colors = baseLightPalette
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColors provides colors
    ){
        content()
    }
}