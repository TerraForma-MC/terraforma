package com.terraforma.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.terraforma.app.navigation.Routes
import com.terraforma.app.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TERRAFORMA",
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                letterSpacing = 3.sp,
                color = Accent
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "v1.0",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                color = TextDisabled
            )
        }

        HorizontalDivider(color = Divider, thickness = 1.dp)

        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Beyond a\nMinecraft Launcher.",
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Platform for Java, servers, development,\ntesting, and terminal workflows.",
                fontFamily = IBMPlexSans,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Primary actions
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "CORE",
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                color = TextDisabled
            )
            Spacer(modifier = Modifier.height(12.dp))

            HomeNavItem(label = "Play Minecraft", sub = "Launch client", accent = true) {
                navController.navigate(Routes.PLAY_MINECRAFT)
            }
            Spacer(modifier = Modifier.height(1.dp))
            HomeNavItem(label = "Run Servers", sub = "Manage server instances") {
                navController.navigate(Routes.RUN_SERVERS)
            }
            Spacer(modifier = Modifier.height(1.dp))
            HomeNavItem(label = "Development", sub = "Plugins, mods, publishing") {
                navController.navigate(Routes.DEVELOPMENT)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "SHELL",
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                color = TextDisabled
            )
            Spacer(modifier = Modifier.height(12.dp))

            HomeNavItem(label = "Sessions", sub = "Active sessions and groups") {
                navController.navigate(Routes.SESSIONS)
            }
            Spacer(modifier = Modifier.height(1.dp))
            HomeNavItem(label = "Terminal", sub = "Shell and command interface") {
                navController.navigate(Routes.TERMINAL)
            }
            Spacer(modifier = Modifier.height(1.dp))
            HomeNavItem(label = "Storage", sub = "Worlds, backups, archives") {
                navController.navigate(Routes.STORAGE)
            }
            Spacer(modifier = Modifier.height(1.dp))
            HomeNavItem(label = "Settings", sub = "Workspace and preferences") {
                navController.navigate(Routes.SETTINGS)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun HomeNavItem(
    label: String,
    sub: String,
    accent: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (accent) AccentMuted else SurfaceContainer)
            .border(1.dp, if (accent) Accent.copy(alpha = 0.4f) else Divider)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = if (accent) Accent else TextPrimary
            )
            Text(
                text = sub,
                fontFamily = IBMPlexSans,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }
        Text(
            text = "→",
            fontFamily = JetBrainsMono,
            fontSize = 14.sp,
            color = if (accent) Accent else TextDisabled
        )
    }
}