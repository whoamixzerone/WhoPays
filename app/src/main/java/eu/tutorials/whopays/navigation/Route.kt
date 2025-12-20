package eu.tutorials.whopays.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Round : Route

    @Serializable
    data class SlotMachine(val round: Int) : Route
}
