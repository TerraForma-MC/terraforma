package com.terraforma.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeState(
    val runningServers: Int = 0,
    val activeMinecraftInstances: Int = 0,
    val activeSessions: Int = 0,
    val storageUsedGb: Float = 0f
)

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadStatus()
    }

    private fun loadStatus() {
        _state.value = HomeState(
            runningServers = 2,
            activeMinecraftInstances = 0,
            activeSessions = 3,
            storageUsedGb = 5.64f
        )
    }

    fun refresh() {
        loadStatus()
    }
}