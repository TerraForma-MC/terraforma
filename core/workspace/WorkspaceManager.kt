package com.terraforma.core.workspace

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class WorkspaceStorage(private val baseDir: File) {

    init {
        baseDir.mkdirs()
    }

    suspend fun save(workspace: Workspace): Boolean = withContext(Dispatchers.IO) {
        try {
            workspace.root.mkdirs()
            workspace.srcDir.mkdirs()
            workspace.buildDir.mkdirs()
            workspace.libsDir.mkdirs()
            workspace.configDir.mkdirs()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun delete(workspace: Workspace): Boolean = withContext(Dispatchers.IO) {
        workspace.root.deleteRecursively()
    }

    suspend fun exists(workspaceId: String): Boolean = withContext(Dispatchers.IO) {
        File(baseDir, workspaceId).exists()
    }

    fun listAll(): List<File> = baseDir.listFiles()?.filter { it.isDirectory } ?: emptyList()

    fun sizeOf(workspace: Workspace): Long {
        return workspace.root.walkTopDown().sumOf { if (it.isFile) it.length() else 0L }
    }
}