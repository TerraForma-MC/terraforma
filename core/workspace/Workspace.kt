package com.terraforma.core.workspace

import java.io.File

data class Workspace(
    val id: String,
    val name: String,
    val rootPath: String,
    val template: WorkspaceTemplate,
    val createdAt: Long = System.currentTimeMillis(),
    val lastOpenedAt: Long = System.currentTimeMillis()
) {
    val root: File get() = File(rootPath)
    val srcDir: File get() = File(rootPath, "src")
    val buildDir: File get() = File(rootPath, "build")
    val libsDir: File get() = File(rootPath, "libs")
    val configDir: File get() = File(rootPath, "config")

    fun exists(): Boolean = root.exists() && root.isDirectory
}