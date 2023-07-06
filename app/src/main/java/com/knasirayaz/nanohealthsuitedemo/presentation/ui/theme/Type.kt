package com.knasirayaz.nanohealthsuitedemo.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp
import com.knasirayaz.nanohealthsuitedemo.R

// Set of Material typography styles to start with

val SF_PRO = FontFamily(
    Font(R.font.regular),
    Font(R.font.medium),
    Font(R.font.bold)
)

val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        baselineShift = BaselineShift(-.2f)

    ),
    labelMedium = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 15.sp,
        baselineShift = BaselineShift(-.2f)
    ),
    labelLarge = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = SF_PRO,
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )


)