package com.terraforma.core.workspace

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID

class WorkspaceManager(context: Context) {

    private val baseDir = File(context.filesDir, "workspaces")
    private val storage = WorkspaceStorage(baseDir)

    private val _workspaces = MutableStateFlow<List<Workspace>>(emptyList())
    val workspaces: StateFlow<List<Workspace>> = _workspaces.asStateFlow()

    private val _activeWorkspace = MutableStateFlow<Workspace?>(null)
    val activeWorkspace: StateFlow<Workspace?> = _activeWorkspace.asStateFlow()

    init {
        baseDir.mkdirs()
        loadWorkspaces()
    }

    suspend fun create(
        name: String,
        template: WorkspaceTemplate
    ): Workspace = withContext(Dispatchers.IO) {
        val id = UUID.randomUUID().toString()
        val workspacePath = File(baseDir, id).absolutePath
        val workspace = Workspace(
            id = id,
            name = name,
            rootPath = workspacePath,
            template = template
        )
        storage.save(workspace)
        applyTemplate(workspace)
        _workspaces.value = _workspaces.value + workspace
        workspace
    }

    suspend fun delete(workspace: Workspace): Boolean = withContext(Dispatchers.IO) {
        val removed = storage.delete(workspace)
        if (removed) {
            _workspaces.value = _workspaces.value.filter { it.id != workspace.id }
            if (_activeWorkspace.value?.id == workspace.id) {
                _activeWorkspace.value = null
            }
        }
        removed
    }

    fun open(workspace: Workspace) {
        _activeWorkspace.value = workspace.copy(lastOpenedAt = System.currentTimeMillis())
    }

    fun close() {
        _activeWorkspace.value = null
    }

    fun get(id: String): Workspace? = _workspaces.value.firstOrNull { it.id == id }

    private fun loadWorkspaces() {
        val dirs = storage.listAll()
        _workspaces.value = dirs.mapNotNull { dir ->
            if (dir.exists()) {
                Workspace(
                    id = dir.name,
                    name = dir.name,
                    rootPath = dir.absolutePath,
                    template = WorkspaceTemplate.BLANK
                )
            } else null
        }
    }

    private fun applyTemplate(workspace: Workspace) {
        when (workspace.template) {
            WorkspaceTemplate.PAPER_PLUGIN -> {
                File(workspace.srcDir, "main/java").mkdirs()
                File(workspace.srcDir, "main/resources").mkdirs()
                File(workspace.root, "build.gradle.kts").createNewFile()
                File(workspace.root, "settings.gradle.kts").createNewFile()
                File(workspace.srcDir, "main/resources/plugin.yml").createNewFile()
            }
            WorkspaceTemplate.VELOCITY_PLUGIN -> {
                File(workspace.srcDir, "main/java").mkdirs()
                File(workspace.srcDir, "main/resources").mkdirs()
                File(workspace.root, "build.gradle.kts").createNewFile()
                File(workspace.root, "settings.gradle.kts").createNewFile()
            }
            WorkspaceTemplate.FABRIC_MOD -> {
                File(workspace.srcDir, "main/java").mkdirs()
                File(workspace.srcDir, "main/resources").mkdirs()
                File(workspace.root, "build.gradle").createNewFile()
                File(workspace.root, "gradle.properties").createNewFile()
                File(workspace.srcDir, "main/resources/fabric.mod.json").createNewFile()
            }
            WorkspaceTemplate.NEOFORGE_MOD -> {
                File(workspace.srcDir, "main/java").mkdirs()
                File(workspace.srcDir, "main/resources").mkdirs()
                File(workspace.root, "build.gradle").createNewFile()
                File(workspace.root, "gradle.properties").createNewFile()
                File(workspace.srcDir, "main/resources/META-INF").mkdirs()
            }
            WorkspaceTemplate.BLANK, WorkspaceTemplate.CUSTOM -> Unit
        }
    }
}