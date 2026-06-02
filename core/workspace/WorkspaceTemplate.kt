package com.terraforma.core.workspace

enum class WorkspaceTemplate(val displayName: String, val description: String) {
    PAPER_PLUGIN("Paper Plugin", "Spigot/Paper plugin project with Gradle"),
    VELOCITY_PLUGIN("Velocity Plugin", "Velocity proxy plugin project"),
    FABRIC_MOD("Fabric Mod", "Fabric mod project with Loom"),
    NEOFORGE_MOD("NeoForge Mod", "NeoForge mod project with MDK"),
    BLANK("Blank", "Empty workspace with no template"),
    CUSTOM("Custom", "User-defined template")
}