package com.terraforma.core.sessions

import java.util.UUID

enum class SessionType { SERVER, MINECRAFT, TERMINAL, DEVELOPMENT, CUSTOM }
enum class SessionStatus { ACTIVE, HIBERNATED, STOPPED, CRASHED }

data class Session(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: SessionType,
    val status: SessionStatus = SessionStatus.ACTIVE,
    val groupId: String? = null,
    val pid: Int? = null,
    val startedAt: Long = System.currentTimeMillis(),
    val hibernatedAt: Long? = null,
    val metadata: Map<String, String> = emptyMap()
) {
    val isActive: Boolean get() = status == SessionStatus.ACTIVE
    val isHibernated: Boolean get() = status == SessionStatus.HIBERNATED

    val uptimeMs: Long get() = System.currentTimeMillis() - startedAt

    val formattedUptime: String get() {
        val total = uptimeMs / 1000
        val days = total / 86400
        val hours = (total % 86400) / 3600
        val minutes = (total % 3600) / 60
        val seconds = total % 60
        return when {
            days > 0 -> "${days}d ${hours}h"
            hours > 0 -> "${hours}h ${minutes}m"
            minutes > 0 -> "${minutes}m ${seconds}s"
            else -> "${seconds}s"
        }
    }
}