package com.terraforma.core.runtime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class RuntimeInstaller(
    private val installDir: File,
    private val verifier: RuntimeVerifier
) {

    sealed class InstallResult {
        data class Success(val profile: RuntimeProfile) : InstallResult()
        data class Failure(val reason: String) : InstallResult()
    }

    suspend fun install(
        downloadUrl: String,
        profileId: String,
        profileName: String,
        javaVersion: Int,
        maxHeapMb: Int = 2048,
        minHeapMb: Int = 512,
        onProgress: (Float) -> Unit = {}
    ): InstallResult = withContext(Dispatchers.IO) {
        try {
            val targetDir = File(installDir, profileId)
            targetDir.mkdirs()

            val archiveFile = File(targetDir, "runtime.tar.gz")
            downloadFile(downloadUrl, archiveFile, onProgress)

            extractArchive(archiveFile, targetDir)
            archiveFile.delete()

            val javaPath = findJavaBinary(targetDir)
                ?: return@withContext InstallResult.Failure("Could not locate java binary after extraction")

            val verification = verifier.verifyJavaBinary(javaPath)
            if (!verification.isValid) {
                return@withContext InstallResult.Failure(verification.reason)
            }

            val profile = RuntimeProfile(
                id = profileId,
                name = profileName,
                javaPath = javaPath,
                javaVersion = javaVersion,
                minHeapMb = minHeapMb,
                maxHeapMb = maxHeapMb
            )

            InstallResult.Success(profile)
        } catch (e: Exception) {
            InstallResult.Failure(e.message ?: "Unknown installation error")
        }
    }

    suspend fun uninstall(profileId: String): Boolean = withContext(Dispatchers.IO) {
        val targetDir = File(installDir, profileId)
        targetDir.deleteRecursively()
    }

    private fun downloadFile(url: String, destination: File, onProgress: (Float) -> Unit) {
        val connection = URL(url).openConnection()
        val totalBytes = connection.contentLengthLong
        var downloadedBytes = 0L

        connection.getInputStream().use { input ->
            destination.outputStream().use { output ->
                val buffer = ByteArray(8192)
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                    downloadedBytes += read
                    if (totalBytes > 0) onProgress(downloadedBytes.toFloat() / totalBytes)
                }
            }
        }
    }

    private fun extractArchive(archive: File, destination: File) {
        val process = ProcessBuilder("tar", "-xzf", archive.absolutePath, "-C", destination.absolutePath)
            .redirectErrorStream(true)
            .start()
        process.waitFor()
    }

    private fun findJavaBinary(dir: File): String? {
        return dir.walkTopDown()
            .firstOrNull { it.name == "java" && it.canExecute() }
            ?.absolutePath
    }
}