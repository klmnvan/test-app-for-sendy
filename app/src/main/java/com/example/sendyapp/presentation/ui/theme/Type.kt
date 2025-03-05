package com.example.sendyapp.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class Typography(
    val titleScreen: TextStyle = TextStyle(),
    val tittleField: TextStyle = TextStyle(),
    val textHint: TextStyle = TextStyle(),
    val textInField: TextStyle = TextStyle(),
    val textButton: TextStyle = TextStyle()
)

val typography = Typography(
    titleScreen = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        lineHeight = 20.sp,
        color = Black
    ),
    tittleField = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = Black
    ),
    textHint = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Gray
    ),
    textInField = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Black
    ),
    textButton = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
)

val LocalTypography = staticCompositionLocalOf { typography }