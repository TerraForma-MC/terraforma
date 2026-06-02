package com.terraforma.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SessionState(
    val id: String,
    val name: String,
    val type: String,
    val isActive: Boolean,
    val uptime: String
)

class SessionViewModel : ViewModel() {

    private val _sessions = MutableStateFlow<List<SessionState>>(emptyList())
    val sessions: StateFlow<List<SessionState>> = _sessions.asStateFlow()

    init {
        loadSessions()
    }

    private fun loadSessions() {
        _sessions.value = listOf(
            SessionState("s1", "server-main", "Paper Server", true, "3h 21m"),
            SessionState("s2", "proxy-01", "Velocity Proxy", true, "1d 4h"),
            SessionState("s3", "plugin-dev", "Development", true, "47m"),
            SessionState("s4", "terminal-1", "Terminal", false, "—"),
        )
    }

    fun hibernateSession(id: String) {
        _sessions.value = _sessions.value.map { session ->
            if (session.id == id) session.copy(isActive = false) else session
        }
    }

    fun terminateSession(id: String) {
        _sessions.value = _sessions.value.filter { it.id != id }
    }
}