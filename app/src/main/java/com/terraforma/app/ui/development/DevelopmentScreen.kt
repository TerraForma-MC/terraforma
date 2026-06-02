package com.terraforma.app.ui.development

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.terraforma.app.ui.theme.*

@Composable
fun DevelopmentScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        ScreenHeader(title = "Development", onBack = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel("PROJECT HUB")

        Spacer(modifier = Modifier.height(12.dp))

        val devItems = listOf(
            Triple("Develop Plugins", "Create and build Spigot/Paper plugins", Routes.NOOP),
            Triple("Develop Mods", "Create Fabric and NeoForge mods", Routes.NOOP),
            Triple("Project Hub", "All projects in one place", Routes.NOOP),
            Triple("Dependency Manager", "Maven and Gradle dependencies", Routes.NOOP),
            Triple("Documentation", "API docs and offline reference", Routes.NOOP),
            Triple("API Browser", "Browse Bukkit, Paper, Fabric APIs", Routes.NOOP),
        )

        devItems.forEach { (title, sub, _) ->
            DevNavItem(title = title, sub = sub, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("TESTING ENVIRONMENTS")

        Spacer(modifier = Modifier.height(12.dp))

        val testers = listOf(
            "plugin_tester" to "Spigot / Paper plugin testing",
            "velocity_tester" to "Proxy plugin testing",
            "folia_tester" to "Region-threaded server testing",
            "fabric_tester" to "Fabric mod testing",
            "neoforge_tester" to "NeoForge mod testing",
        )

        testers.forEach { (id, desc) ->
            TesterRow(id = id, desc = desc, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
            TesterRowWide(
                id = "Server Simulator",
                desc = "Simulate full server load and player behavior",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
            TesterRowWide(
                id = "Plugin Benchmark",
                desc = "Memory and performance profiling",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("PUBLISHING")

        Spacer(modifier = Modifier.height(12.dp))

        PublishPanel(modifier = Modifier.padding(horizontal = 20.dp))

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun DevNavItem(title: String, sub: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(1.dp, Divider)
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = TextPrimary
            )
            Text(
                text = sub,
                fontFamily = IBMPlexSans,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }
        Text("→", fontFamily = JetBrainsMono, fontSize = 13.sp, color = TextDisabled)
    }
}

@Composable
private fun TesterRow(id: String, desc: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(1.dp, Divider)
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = id,
            fontFamily = JetBrainsMono,
            fontSize = 12.sp,
            color = Accent,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = desc,
            fontFamily = IBMPlexSans,
            fontSize = 11.sp,
            color = TextSecondary
        )
    }
}

@Composable
private fun TesterRowWide(id: String, desc: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(SurfaceContainer)
            .border(1.dp, Divider)
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 13.dp)
    ) {
        Text(
            text = id,
            fontFamily = IBMPlexSans,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = TextPrimary
        )
        Text(
            text = desc,
            fontFamily = IBMPlexSans,
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}

@Composable
private fun PublishPanel(modifier: Modifier = Modifier) {
    val plugins = listOf("MyEconomy", "TerraChat", "BetterHomes", "ServerToolkit")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(1.dp, Divider)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Upload Plugin",
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Plugins Found: ${plugins.size}",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                color = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        plugins.forEach { name ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(Accent)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = name,
                    fontFamily = JetBrainsMono,
                    fontSize = 12.sp,
                    color = TextPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Target:",
                fontFamily = IBMPlexSans,
                fontSize = 12.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Spigot",
                fontFamily = JetBrainsMono,
                fontSize = 12.sp,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(AccentMuted)
                    .border(1.dp, Accent.copy(alpha = 0.4f))
                    .clickable { }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "UPLOAD SELECTED",
                    fontFamily = JetBrainsMono,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp,
                    color = Accent
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Accent)
                    .clickable { }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "UPLOAD ALL",
                    fontFamily = JetBrainsMono,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp,
                    color = Background
                )
            }
        }
    }
}

private object Routes {
    const val NOOP = ""
}