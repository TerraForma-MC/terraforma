package com.terraforma.app.ui.settings

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraforma.app.ui.theme.*
import com.terraforma.app.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val settings by settingsViewModel.settings.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        ScreenHeader(title = "Settings", onBack = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel("EDITOR")

        Spacer(modifier = Modifier.height(12.dp))

        SettingsDropdown(
            label = "Default Editor",
            value = settings.defaultEditor,
            options = listOf("Neovim", "Vim", "Nano", "Helix", "TerraForma Editor"),
            onSelect = { settingsViewModel.setEditor(it) },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        SettingsDropdown(
            label = "Package Manager Style",
            value = settings.packageManagerStyle,
            options = listOf(
                "TerraForma (app)", "pkg", "apt", "pacman", "dnf", "zypper", "apk", "custom"
            ),
            onSelect = { settingsViewModel.setPackageManagerStyle(it) },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        SettingsToggle(
            label = "Show Alias In UI",
            sub = "Display package manager alias in shell and UI",
            enabled = settings.showAliasInUi,
            onToggle = { settingsViewModel.toggleAlias() },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("VERSION")

        Spacer(modifier = Modifier.height(12.dp))

        SettingsDropdown(
            label = "Minecraft Version",
            value = settings.minecraftVersion,
            options = listOf("26.2", "26.1", "25.4", "25.3"),
            onSelect = { settingsViewModel.setMinecraftVersion(it) },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        SettingsDropdown(
            label = "Server Software",
            value = settings.serverSoftware,
            options = listOf("Paper", "Purpur", "Velocity", "Fabric", "NeoForge", "Vanilla"),
            onSelect = { settingsViewModel.setServerSoftware(it) },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        SettingsDropdown(
            label = "Server Version",
            value = settings.serverVersion,
            options = listOf("26.2", "26.1", "25.4"),
            onSelect = { settingsViewModel.setServerVersion(it) },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(Accent)
                .clickable { settingsViewModel.applyChanges() }
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "APPLY CHANGES",
                fontFamily = JetBrainsMono,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                letterSpacing = 2.sp,
                color = Background
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("WORKSPACE")

        Spacer(modifier = Modifier.height(12.dp))

        listOf("Manage Workspace", "Manage Packages").forEach { label ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(SurfaceContainer)
                    .border(1.dp, Divider)
                    .clickable { }
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    fontFamily = IBMPlexSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = TextPrimary,
                    modifier = Modifier.weight(1f)
                )
                Text("→", fontFamily = JetBrainsMono, fontSize = 13.sp, color = TextDisabled)
            }
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("ABOUT")

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(SurfaceContainer)
                .border(1.dp, Divider)
                .padding(16.dp)
        ) {
            AboutRow("Version", "1.0.0")
            Spacer(modifier = Modifier.height(8.dp))
            AboutRow("Build", "2025.06.01")
            Spacer(modifier = Modifier.height(8.dp))
            AboutRow("Target SDK", "35")
            Spacer(modifier = Modifier.height(8.dp))
            AboutRow("Min SDK", "26")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(SurfaceContainer)
                .border(1.dp, Divider)
                .clickable { }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Replay Intro",
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )
            Text("→", fontFamily = JetBrainsMono, fontSize = 13.sp, color = TextDisabled)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun AboutRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
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
    }
}

@Composable
private fun SettingsDropdown(
    label: String,
    value: String,
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer)
                .border(1.dp, if (expanded) Accent.copy(alpha = 0.5f) else Divider)
                .clickable { expanded = !expanded }
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
                text = if (expanded) "▲" else "▼",
                fontFamily = JetBrainsMono,
                fontSize = 10.sp,
                color = TextDisabled
            )
        }

        if (expanded) {
            options.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceVariant)
                        .border(1.dp, DividerSubtle)
                        .clickable {
                            onSelect(option)
                            expanded = false
                        }
                        .padding(horizontal = 16.dp, vertical = 11.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(if (option == value) Accent else TextDisabled)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = option,
                        fontFamily = JetBrainsMono,
                        fontSize = 12.sp,
                        color = if (option == value) Accent else TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsToggle(
    label: String,
    sub: String,
    enabled: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(1.dp, Divider)
            .clickable { onToggle() }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontFamily = IBMPlexSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = TextPrimary
            )
            Text(
                text = sub,
                fontFamily = IBMPlexSans,
                fontSize = 11.sp,
                color = TextSecondary
            )
        }
        Text(
            text = if (enabled) "ENABLED" else "DISABLED",
            fontFamily = JetBrainsMono,
            fontSize = 10.sp,
            letterSpacing = 1.sp,
            color = if (enabled) Accent else TextDisabled
        )
    }
}