package com.terraforma.core.runtime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RuntimeUpdater(
    private val registry: RuntimeRegistry,
    private val installer: RuntimeInstaller
) {

    data class UpdateInfo(
        val profileId: String,
        val currentVersion: String,
        val latestVersion: String,
        val downloadUrl: String,
        val isUpdateAvailable: Boolean
    )

    suspend fun checkForUpdates(profileId: String): UpdateInfo? = withContext(Dispatchers.IO) {
        val profile = registry.get(profileId) ?: return@withContext null
        // In a real implementation, this would query a remote manifest
        // to compare installed vs latest available version
        UpdateInfo(
            profileId = profileId,
            currentVersion = "21.0.3",
            latestVersion = "21.0.3",
            downloadUrl = "",
            isUpdateAvailable = false
        )
    }

    suspend fun update(
        profileId: String,
        downloadUrl: String,
        onProgress: (Float) -> Unit = {}
    ): Boolean = withContext(Dispatchers.IO) {
        val profile = registry.get(profileId) ?: return@withContext false
        val result = installer.install(
            downloadUrl = downloadUrl,
            profileId = profileId,
            profileName = profile.name,
            javaVersion = profile.javaVersion,
            maxHeapMb = profile.maxHeapMb,
            minHeapMb = profile.minHeapMb,
            onProgress = onProgress
        )
        when (result) {
            is RuntimeInstaller.InstallResult.Success -> {
                registry.register(result.profile.copy(isDefault = profile.isDefault))
                true
            }
            is RuntimeInstaller.InstallResult.Failure -> false
        }
    }
}