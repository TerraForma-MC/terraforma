package com.terraforma.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppSettings(
    val defaultEditor: String = "Neovim",
    val packageManagerStyle: String = "TerraForma (app)",
    val showAliasInUi: Boolean = true,
    val minecraftVersion: String = "26.2",
    val serverSoftware: String = "Paper",
    val serverVersion: String = "26.2"
)

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
        _settings.value = _settings.value.copy(serverSoftware = software)
    }

    fun setServerVersion(version: String) {
        _settings.value = _settings.value.copy(serverVersion = version)
    }

    fun applyChanges() {
        // Persist to DataStore / disk — wired up when core/ module is ready
    }
}