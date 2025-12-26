package eu.tutorials.whopays.navigation

import androidx.navigation3.runtime.NavKey
import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Round : Route

    @Serializable
    data class SlotMachine(val round: Int) : Route

    @Serializable
    data class ScoreBoard(val history: ImmutableList<SlotResult>) : Route
}
