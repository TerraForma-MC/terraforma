package com.terraforma.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TerraFormaDarkColorScheme = darkColorScheme(
    primary = Accent,
    onPrimary = Color(0xFF001A0D),
    primaryContainer = AccentMuted,
    onPrimaryContainer = Accent,
    secondary = TextSecondary,
    onSecondary = Background,
    secondaryContainer = SurfaceVariant,
    onSecondaryContainer = TextPrimary,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = Divider,
    outlineVariant = DividerSubtle,
    error = StatusError,
    onError = Color(0xFF1A0000),
)

@Composable
fun TerraFormaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TerraFormaDarkColorScheme,
        typography = TerraFormaTypography,
        content = content
    )
}