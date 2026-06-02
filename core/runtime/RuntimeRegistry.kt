package com.terraforma.core.runtime

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RuntimeRegistry {

    private val _profiles = MutableStateFlow<Map<String, RuntimeProfile>>(emptyMap())
    val profiles: StateFlow<Map<String, RuntimeProfile>> = _profiles.asStateFlow()

    fun register(profile: RuntimeProfile) {
        _profiles.value = _profiles.value + (profile.id to profile)
    }

    fun unregister(id: String) {
        _profiles.value = _profiles.value - id
    }

    fun get(id: String): RuntimeProfile? = _profiles.value[id]

    fun getDefault(): RuntimeProfile? = _profiles.value.values.firstOrNull { it.isDefault }

    fun all(): List<RuntimeProfile> = _profiles.value.values.toList()

    fun setDefault(id: String) {
        _profiles.value = _profiles.value.mapValues { (key, profile) ->
            profile.copy(isDefault = key == id)
        }
    }
}