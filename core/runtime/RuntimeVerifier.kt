package com.terraforma.core.runtime

import java.io.File
import java.security.MessageDigest

class RuntimeVerifier {

    data class VerificationResult(
        val isValid: Boolean,
        val reason: String = ""
    )

    fun verifyJavaBinary(javaPath: String): VerificationResult {
        val file = File(javaPath)
        if (!file.exists()) return VerificationResult(false, "Java binary not found at $javaPath")
        if (!file.canExecute()) return VerificationResult(false, "Java binary is not executable")
        return VerificationResult(true)
    }

    fun verifyFileChecksum(filePath: String, expectedSha256: String): VerificationResult {
        val file = File(filePath)
        if (!file.exists()) return VerificationResult(false, "File not found: $filePath")
        val actual = sha256(file)
        if (actual != expectedSha256.lowercase()) {
            return VerificationResult(false, "Checksum mismatch: expected $expectedSha256, got $actual")
        }
        return VerificationResult(true)
    }

    fun verifyDirectory(dirPath: String): VerificationResult {
        val dir = File(dirPath)
        if (!dir.exists()) return VerificationResult(false, "Directory not found: $dirPath")
        if (!dir.isDirectory) return VerificationResult(false, "Path is not a directory: $dirPath")
        if (!dir.canRead()) return VerificationResult(false, "Directory is not readable: $dirPath")
        return VerificationResult(true)
    }

    private fun sha256(file: File): String {
        val digest = MessageDigest.getInstance("SHA-256")
        file.inputStream().use { stream ->
            val buffer = ByteArray(8192)
            var read: Int
            while (stream.read(buffer).also { read = it } != -1) {
                digest.update(buffer, 0, read)
            }
        }
        return digest.digest().joinToString("") { "%02x".format(it) }
    }
}