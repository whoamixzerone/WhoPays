package eu.tutorials.whopays.presentation.screen.slotmachine

import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList

sealed interface SlotMachineEffect {
    data class NavigationToResult(val history: ImmutableList<SlotResult>) : SlotMachineEffect
}