package com.terraforma.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Using JetBrains Mono for monospace terminal feel
// and IBM Plex Sans for UI text
val JetBrainsMono = FontFamily(
    Font(com.terraforma.app.R.font.jetbrainsmono_regular, FontWeight.Normal),
    Font(com.terraforma.app.R.font.jetbrainsmono_medium, FontWeight.Medium),
    Font(com.terraforma.app.R.font.jetbrainsmono_bold, FontWeight.Bold),
)

val IBMPlexSans = FontFamily(
    Font(com.terraforma.app.R.font.ibmplexsans_regular, FontWeight.Normal),
    Font(com.terraforma.app.R.font.ibmplexsans_medium, FontWeight.Medium),
    Font(com.terraforma.app.R.font.ibmplexsans_semibold, FontWeight.SemiBold),
    Font(com.terraforma.app.R.font.ibmplexsans_bold, FontWeight.Bold),
)

val TerraFormaTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.5).sp,
        color = TextPrimary
    ),
    displayMedium = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.3).sp,
        color = TextPrimary
    ),
    headlineLarge = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        color = TextPrimary
    ),
    headlineMedium = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        color = TextPrimary
    ),
    titleLarge = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = TextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = TextPrimary
    ),
    bodyLarge = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        color = TextPrimary
    ),
    bodyMedium = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 19.sp,
        color = TextSecondary
    ),
    bodySmall = TextStyle(
        fontFamily = IBMPlexSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 17.sp,
        color = TextSecondary
    ),
    labelLarge = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.3.sp,
        color = TextPrimary
    ),
    labelMedium = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp,
        color = TextSecondary
    ),
    labelSmall = TextStyle(
        fontFamily = JetBrainsMono,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.4.sp,
        color = TextDisabled
    ),
)