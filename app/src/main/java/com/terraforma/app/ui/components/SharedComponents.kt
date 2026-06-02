package com.terraforma.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraforma.app.ui.theme.*

@Composable
fun ScreenHeader(title: String, onBack: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "←",
                fontFamily = JetBrainsMono,
                fontSize = 16.sp,
                color = TextSecondary,
                modifier = Modifier
                    .clickable { onBack() }
                    .padding(end = 16.dp)
            )
            Text(
                text = title,
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = TextPrimary
            )
        }
        HorizontalDivider(color = Divider, thickness = 1.dp)
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        fontFamily = JetBrainsMono,
        fontSize = 10.sp,
        letterSpacing = 2.sp,
        color = TextDisabled,
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}

@Composable
fun ConfigRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(1.dp, Divider)
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontFamily = IBMPlexSans,
            fontSize = 13.sp,
            color = TextSecondary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontFamily = JetBrainsMono,
            fontSize = 12.sp,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "▼",
            fontFamily = JetBrainsMono,
            fontSize = 10.sp,
            color = TextDisabled
        )
    }
}

@Composable
fun VersionRow(
    version: String,
    type: String,
    isCurrent: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isCurrent) AccentMuted else SurfaceContainer)
            .border(1.dp, if (isCurrent) Accent.copy(alpha = 0.4f) else Divider)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = version,
            fontFamily = JetBrainsMono,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = if (isCurrent) Accent else TextPrimary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = type,
            fontFamily = JetBrainsMono,
            fontSize = 10.sp,
            color = TextSecondary
        )
        if (isCurrent) {
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "CURRENT",
                fontFamily = JetBrainsMono,
                fontSize = 9.sp,
                letterSpacing = 1.sp,
                color = Accent
            )
        }
    }
}