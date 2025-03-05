package com.example.sendyapp.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF00BFFF)
val Black = Color(0xFF181818)
val White = Color(0xFFFFFFFF)
val Gray = Color(0xFF7C7C7C)

data class ColorPalette(
    val background: Color = Color.Transparent,
    val primary: Color = Color.Transparent,
    val textTittle: Color = Color.Transparent,
    val container: Color = Color.Transparent,
)

val baseLightPalette = ColorPalette(
    background = White,
    primary = Blue,
    textTittle = Black,
    container = Gray,
)

val LocalColors = staticCompositionLocalOf { baseLightPalette }