package com.terraforma.core.runtime

import android.content.Context
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class RuntimeManager(context: Context) {

    private val installDir = File(context.filesDir, "runtimes")
    private val verifier = RuntimeVerifier()
    private val installer = RuntimeInstaller(installDir, verifier)
    val registry = RuntimeRegistry()
    val updater = RuntimeUpdater(registry, installer)

    val profiles: StateFlow<Map<String, RuntimeProfile>> = registry.profiles

    init {
        installDir.mkdirs()
        loadPersistedProfiles()
    }

    fun getProfile(id: String): RuntimeProfile? = registry.get(id)

    fun getDefaultProfile(): RuntimeProfile? = registry.getDefault()

    fun setDefaultProfile(id: String) = registry.setDefault(id)

    fun allProfiles(): List<RuntimeProfile> = registry.all()

    suspend fun installRuntime(
        downloadUrl: String,
        profileId: String,
        profileName: String,
        javaVersion: Int,
        maxHeapMb: Int = 2048,
        onProgress: (Float) -> Unit = {}
    ): RuntimeInstaller.InstallResult {
        val result = installer.install(
            downloadUrl = downloadUrl,
            profileId = profileId,
            profileName = profileName,
            javaVersion = javaVersion,
            maxHeapMb = maxHeapMb,
            onProgress = onProgress
        )
        if (result is RuntimeInstaller.InstallResult.Success) {
            registry.register(result.profile)
            if (registry.getDefault() == null) registry.setDefault(result.profile.id)
        }
        return result
    }

    suspend fun uninstallRuntime(id: String): Boolean {
        val removed = installer.uninstall(id)
        if (removed) registry.unregister(id)
        return removed
    }

    fun buildJvmArgs(profile: RuntimeProfile): List<String> {
        return buildList {
            add("-Xms${profile.minHeapMb}m")
            add("-Xmx${profile.maxHeapMb}m")
            add("-XX:+UseG1GC")
            add("-XX:+ParallelRefProcEnabled")
            add("-XX:MaxGCPauseMillis=200")
            add("-XX:+UnlockExperimentalVMOptions")
            add("-XX:+DisableExplicitGC")
            add("-XX:+AlwaysPreTouch")
            add("-XX:G1NewSizePercent=30")
            add("-XX:G1MaxNewSizePercent=40")
            add("-XX:G1HeapRegionSize=8M")
            add("-XX:G1ReservePercent=20")
            add("-XX:G1HeapWastePercent=5")
            add("-XX:G1MixedGCCountTarget=4")
            add("-XX:InitiatingHeapOccupancyPercent=15")
            add("-XX:G1MixedGCLiveThresholdPercent=90")
            add("-XX:G1RSetUpdatingPauseTimePercent=5")
            add("-XX:SurvivorRatio=32")
            add("-XX:+PerfDisableSharedMem")
            add("-XX:MaxTenuringThreshold=1")
            addAll(profile.jvmArgs)
        }
    }

    private fun loadPersistedProfiles() {
        // Load from DataStore/disk on first boot
        // Bundled default profile using system Java if available
        val systemJava = findSystemJava()
        if (systemJava != null) {
            val default = RuntimeProfile(
                id = "system",
                name = "System Java",
                javaPath = systemJava,
                javaVersion = 21,
                minHeapMb = 512,
                maxHeapMb = 2048,
                isDefault = true
            )
            registry.register(default)
        }
    }

    private fun findSystemJava(): String? {
        val candidates = listOf(
            "/usr/bin/java",
            "/usr/local/bin/java",
            System.getenv("JAVA_HOME")?.let { "$it/bin/java" }
        )
        return candidates.filterNotNull().firstOrNull { File(it).exists() }
    }
}