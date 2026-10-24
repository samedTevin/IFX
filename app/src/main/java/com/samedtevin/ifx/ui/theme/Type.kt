package com.samedtevin.ifx.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samedtevin.ifx.R

// Inter font family with full weight coverage
val Inter = FontFamily(
    Font(R.font.inter_18pt_thin, FontWeight.Thin),
    Font(R.font.inter_18pt_regular, FontWeight.Normal),
    Font(R.font.inter_18pt_medium, FontWeight.Medium),
    Font(R.font.inter_18pt_semibold, FontWeight.SemiBold),
    Font(R.font.inter_18pt_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        lineHeight = 42.sp,
        letterSpacing = (-0.5).sp
    ),
    titleLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 22.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp
    )
)
