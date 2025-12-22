package eu.tutorials.whopays.presentation.screen.slotmachine

import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList

sealed interface SlotMachineAction {
    data object OnSpinClick : SlotMachineAction
    data object OnStopClick : SlotMachineAction
    data class OnResultClick(val history: ImmutableList<SlotResult>) : SlotMachineAction
    data class updateNumberOne(val number: String) : SlotMachineAction
    data class updateNumberTwo(val number: String) : SlotMachineAction
    data class updateOperator(val operator: String) : SlotMachineAction
}