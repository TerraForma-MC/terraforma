package com.terraforma.app.ui.play

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.terraforma.app.ui.theme.*

@Composable
fun PlayMinecraftScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    var selectedVersion by remember { mutableStateOf("26.2") }
    var selectedProfile by remember { mutableStateOf("Default") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        ScreenHeader(title = "Play Minecraft", onBack = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel("LAUNCH CONFIGURATION")

        Spacer(modifier = Modifier.height(12.dp))

        ConfigRow(
            label = "Minecraft Version",
            value = selectedVersion,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(1.dp))
        ConfigRow(
            label = "Profile",
            value = selectedProfile,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(1.dp))
        ConfigRow(
            label = "Java Runtime",
            value = "OpenJDK 21",
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(1.dp))
        ConfigRow(
            label = "RAM Allocation",
            value = "2048 MB",
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("INSTALLED VERSIONS")

        Spacer(modifier = Modifier.height(12.dp))

        val versions = MINECRAFT_VERSIONS.mapIndexed { index, version ->
            Triple(version, if (version.startsWith("26")) "Game Drop" else "Release", index == 0)
        }

        versions.forEach { (version, type, current) ->
            VersionRow(
                version = version,
                type = type,
                isCurrent = current,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Launch button
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(Accent)
                .clickable { }
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "LAUNCH",
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                letterSpacing = 3.sp,
                color = Background
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}