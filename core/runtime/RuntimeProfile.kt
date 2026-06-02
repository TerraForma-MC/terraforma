package com.terraforma.core.runtime

data class RuntimeProfile(
    val id: String,
    val name: String,
    val javaPath: String,
    val javaVersion: Int,
    val minHeapMb: Int,
    val maxHeapMb: Int,
    val jvmArgs: List<String> = emptyList(),
    val isDefault: Boolean = false
)