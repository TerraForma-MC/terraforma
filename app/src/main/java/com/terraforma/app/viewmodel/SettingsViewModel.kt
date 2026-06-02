package com.terraforma.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppSettings(
    val defaultEditor: String = "Neovim",
    val packageManagerStyle: String = "TerraForma (app)",
    val showAliasInUi: Boolean = true,
    val minecraftVersion: String = MINECRAFT_VERSIONS.first(),
    val serverSoftware: String = "Paper",
    val serverVersion: String = PAPER_VERSIONS.first()
)

// Minecraft Java Edition — latest (26.1.2) sampai tertua (1.0)
val MINECRAFT_VERSIONS = listOf(
    "26.1.2", "26.1.1", "26.1",
    "1.21.11", "1.21.10", "1.21.9", "1.21.8", "1.21.7", "1.21.6",
    "1.21.5", "1.21.4", "1.21.3", "1.21.2", "1.21.1", "1.21",
    "1.20.6", "1.20.5", "1.20.4", "1.20.3", "1.20.2", "1.20.1", "1.20",
    "1.19.4", "1.19.3", "1.19.2", "1.19.1", "1.19",
    "1.18.2", "1.18.1", "1.18",
    "1.17.1", "1.17",
    "1.16.5", "1.16.4", "1.16.3", "1.16.2", "1.16.1", "1.16",
    "1.15.2", "1.15.1", "1.15",
    "1.14.4", "1.14.3", "1.14.2", "1.14.1", "1.14",
    "1.13.2", "1.13.1", "1.13",
    "1.12.2", "1.12.1", "1.12",
    "1.11.2", "1.11.1", "1.11",
    "1.10.2", "1.10.1", "1.10",
    "1.9.4", "1.9.3", "1.9.2", "1.9.1", "1.9",
    "1.8.9", "1.8.8", "1.8.7", "1.8.6", "1.8.5", "1.8.4", "1.8.3", "1.8.2", "1.8.1", "1.8",
    "1.7.10", "1.7.9", "1.7.8", "1.7.7", "1.7.6", "1.7.5", "1.7.4", "1.7.3", "1.7.2",
    "1.6.4", "1.6.2", "1.6.1",
    "1.5.2", "1.5.1",
    "1.4.7", "1.4.6", "1.4.5", "1.4.4", "1.4.2",
    "1.3.2", "1.3.1",
    "1.2.5", "1.2.4", "1.2.3", "1.2.2", "1.2.1",
    "1.1", "1.0"
)

// Paper — versi MC yang didukung (26.1.2 sampai 1.8)
val PAPER_VERSIONS = listOf(
    "26.1.2",
    "1.21.11", "1.21.10", "1.21.9", "1.21.8", "1.21.7", "1.21.6",
    "1.21.5", "1.21.4", "1.21.3", "1.21.1", "1.21",
    "1.20.6", "1.20.5", "1.20.4", "1.20.2", "1.20.1", "1.20",
    "1.19.4", "1.19.3", "1.19.2", "1.19.1", "1.19",
    "1.18.2", "1.18.1", "1.18",
    "1.17.1", "1.17",
    "1.16.5", "1.16.4", "1.16.3", "1.16.2", "1.16.1",
    "1.15.2", "1.15.1", "1.15",
    "1.14.4", "1.14.3", "1.14.2", "1.14.1",
    "1.13.2", "1.13.1", "1.13",
    "1.12.2", "1.12.1", "1.12",
    "1.11.2", "1.11.1",
    "1.10.2",
    "1.9.4", "1.9",
    "1.8.8"
)

// Purpur — dari API resmi purpurmc.org
val PURPUR_VERSIONS = listOf(
    "26.1.2",
    "1.21.11", "1.21.10", "1.21.9", "1.21.8", "1.21.7", "1.21.6",
    "1.21.5", "1.21.4", "1.21.3", "1.21.1", "1.21",
    "1.20.6", "1.20.4", "1.20.2", "1.20.1", "1.20",
    "1.19.4", "1.19.3", "1.19.2", "1.19.1", "1.19",
    "1.18.2", "1.18.1", "1.18",
    "1.17.1", "1.17",
    "1.16.5", "1.16.4", "1.16.3", "1.16.2", "1.16.1",
    "1.15.2", "1.15.1", "1.15",
    "1.14.4", "1.14.3", "1.14.2", "1.14.1"
)

// Velocity — rilis resmi dari PaperMC (terbaru ke tertua)
val VELOCITY_VERSIONS = listOf(
    "3.4.0",
    "3.3.0", "3.2.0", "3.1.2", "3.1.1", "3.1.0",
    "3.0.0",
    "1.1.9", "1.1.8", "1.1.7", "1.1.6", "1.1.5",
    "1.0.10", "1.0.9", "1.0.8", "1.0.7", "1.0.6",
    "1.0.5", "1.0.4", "1.0.3", "1.0.2", "1.0.1", "1.0.0"
)

// Fabric — Minecraft versions yang punya Fabric support (terbaru ke tertua)
val FABRIC_VERSIONS = listOf(
    "26.1", "26.1.1", "26.1.2",
    "1.21.11", "1.21.10", "1.21.9", "1.21.8", "1.21.7", "1.21.6",
    "1.21.5", "1.21.4", "1.21.3", "1.21.1", "1.21",
    "1.20.6", "1.20.5", "1.20.4", "1.20.2", "1.20.1", "1.20",
    "1.19.4", "1.19.3", "1.19.2", "1.19.1", "1.19",
    "1.18.2", "1.18.1", "1.18",
    "1.17.1", "1.17",
    "1.16.5", "1.16.4", "1.16.3", "1.16.2", "1.16.1",
    "1.15.2", "1.15.1", "1.15",
    "1.14.4", "1.14.3", "1.14.2", "1.14.1",
    "1.13.2"
)

// NeoForge — dimulai dari fork di 1.20.2 (terbaru ke tertua)
val NEOFORGE_VERSIONS = listOf(
    "26.1",
    "1.21.11", "1.21.10", "1.21.9", "1.21.8", "1.21.7", "1.21.6",
    "1.21.5", "1.21.4", "1.21.3", "1.21.1", "1.21",
    "1.20.6", "1.20.5", "1.20.4", "1.20.3", "1.20.2"
)

// Vanilla — sama dengan Minecraft versions (hanya release)
val VANILLA_VERSIONS = MINECRAFT_VERSIONS

fun getVersionsForSoftware(software: String): List<String> = when (software) {
    "Paper"    -> PAPER_VERSIONS
    "Purpur"   -> PURPUR_VERSIONS
    "Velocity" -> VELOCITY_VERSIONS
    "Fabric"   -> FABRIC_VERSIONS
    "NeoForge" -> NEOFORGE_VERSIONS
    "Vanilla"  -> VANILLA_VERSIONS
    else       -> PAPER_VERSIONS
}

class SettingsViewModel : ViewModel() {

    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    fun setEditor(editor: String) {
        _settings.value = _settings.value.copy(defaultEditor = editor)
    }

    fun setPackageManagerStyle(style: String) {
        _settings.value = _settings.value.copy(packageManagerStyle = style)
    }

    fun toggleAlias() {
        _settings.value = _settings.value.copy(showAliasInUi = !_settings.value.showAliasInUi)
    }

    fun setMinecraftVersion(version: String) {
        _settings.value = _settings.value.copy(minecraftVersion = version)
    }

    fun setServerSoftware(software: String) {
        val newVersion = getVersionsForSoftware(software).first()
        _settings.value = _settings.value.copy(
            serverSoftware = software,
            serverVersion = newVersion
        )
    }

    fun setServerVersion(version: String) {
        _settings.value = _settings.value.copy(serverVersion = version)
    }

    fun applyChanges() {
        // Persist ke DataStore — disambungkan saat core/ module selesai
    }
}