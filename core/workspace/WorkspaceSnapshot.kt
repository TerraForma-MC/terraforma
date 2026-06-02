package com.terraforma.core.workspace

data class WorkspaceSnapshot(
    val id: String,
    val workspaceId: String,
    val name: String,
    val description: String,
    val createdAt: Long,
    val snapshotPath: String,
    val sizeBytes: Long
) {
    val formattedSize: String get() {
        return when {
            sizeBytes < 1024 -> "${sizeBytes} B"
            sizeBytes < 1024 * 1024 -> "${"%.1f".format(sizeBytes / 1024.0)} KB"
            sizeBytes < 1024 * 1024 * 1024 -> "${"%.1f".format(sizeBytes / (1024.0 * 1024))} MB"
            else -> "${"%.2f".format(sizeBytes / (1024.0 * 1024 * 1024))} GB"
        }
    }
}