package eu.tutorials.whopays.presentation.screen.slotmachine

import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList

sealed interface SlotMachineAction {
    data object OnSpinClick : SlotMachineAction
    data object OnStopClick : SlotMachineAction
    data class OnResultClick(val history: ImmutableList<SlotResult>) : SlotMachineAction
}