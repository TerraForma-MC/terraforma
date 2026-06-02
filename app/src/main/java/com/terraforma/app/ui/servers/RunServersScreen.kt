package com.terraforma.app.ui.servers

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

data class ServerInstance(
    val name: String,
    val software: String,
    val version: String,
    val isRunning: Boolean,
    val players: Int = 0,
    val tps: Double = 0.0,
    val uptime: String = ""
)

@Composable
fun RunServersScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    val servers = listOf(
        ServerInstance("SurvivalSMP", "Paper", "26.2", true, 4, 20.0, "3h 21m"),
        ServerInstance("TestServer", "Purpur", "26.1", false),
        ServerInstance("ProxyNetwork", "Velocity", "3.4.0", true, 12, 20.0, "1d 4h"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        ScreenHeader(title = "Run Servers", onBack = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel("ACTIVE INSTANCES")

        Spacer(modifier = Modifier.height(12.dp))

        servers.forEach { server ->
            ServerCard(server = server, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        SectionLabel("SERVER SOFTWARE")

        Spacer(modifier = Modifier.height(12.dp))

        val softwareList = listOf(
            "Paper" to "High-performance Spigot fork",
            "Purpur" to "Extended Paper with extra features",
            "Velocity" to "Modern proxy server",
            "Fabric" to "Lightweight mod loader",
            "NeoForge" to "Community Forge fork",
            "Vanilla" to "Official Mojang server"
        )

        softwareList.forEach { (name, desc) ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(SurfaceContainer)
                    .border(1.dp, Divider)
                    .clickable { }
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
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
                Text(
                    text = "NEW",
                    fontFamily = JetBrainsMono,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = Accent
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun ServerCard(server: ServerInstance, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(
                1.dp,
                if (server.isRunning) Accent.copy(alpha = 0.3f) else Divider
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(if (server.isRunning) StatusRunning else StatusStopped)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = server.name,
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (server.isRunning) "RUNNING" else "STOPPED",
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                letterSpacing = 1.sp,
                color = if (server.isRunning) StatusRunning else TextDisabled
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(
                text = "${server.software} ${server.version}",
                fontFamily = JetBrainsMono,
                fontSize = 11.sp,
                color = TextSecondary
            )
        }

        if (server.isRunning) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = DividerSubtle)
            Spacer(modifier = Modifier.height(12.dp))

            Row {
                MetricItem("PLAYERS", "${server.players}")
                Spacer(modifier = Modifier.width(24.dp))
                MetricItem("TPS", "${server.tps}")
                Spacer(modifier = Modifier.width(24.dp))
                MetricItem("UPTIME", server.uptime)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                ActionButton(label = "OPEN") {}
                Spacer(modifier = Modifier.width(8.dp))
                ActionButton(label = "RESTART") {}
                Spacer(modifier = Modifier.width(8.dp))
                ActionButton(label = "STOP", danger = true) {}
            }
        }
    }
}

@Composable
private fun MetricItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontFamily = JetBrainsMono,
            fontSize = 9.sp,
            letterSpacing = 1.sp,
            color = TextDisabled
        )
        Text(
            text = value,
            fontFamily = JetBrainsMono,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = TextPrimary
        )
    }
}

@Composable
private fun ActionButton(label: String, danger: Boolean = false, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(if (danger) StatusError.copy(alpha = 0.1f) else SurfaceVariant)
            .border(1.dp, if (danger) StatusError.copy(alpha = 0.4f) else Divider)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontFamily = JetBrainsMono,
            fontSize = 10.sp,
            letterSpacing = 1.sp,
            color = if (danger) StatusError else TextSecondary
        )
    }
}