package com.example.giphy.ui.res

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.giphy.core.R

object GifTypography {
    val headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulishroman_bold)),
        fontSize = 20.0.sp,
        letterSpacing = 0.0.sp,
        lineHeight = 24.0.sp,
    )
    val titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulish)),
        fontSize = 16.0.sp,
        letterSpacing = 0.0.sp,
        lineHeight = 20.0.sp,
    )
    val titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulishroman_extrabold)),
        fontSize = 16.0.sp,
        letterSpacing = 0.0.sp,
        lineHeight = 20.0.sp,
    )
    val displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulish)),
        fontSize = 28.0.sp,
        letterSpacing = 0.0.sp,
        lineHeight = 34.0.sp,
    )
}
