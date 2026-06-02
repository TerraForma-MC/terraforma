package com.terraforma.core.sessions

data class SessionGroup(
    val id: String,
    val name: String,
    val sessionIds: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
) {
    val sessionCount: Int get() = sessionIds.size
}