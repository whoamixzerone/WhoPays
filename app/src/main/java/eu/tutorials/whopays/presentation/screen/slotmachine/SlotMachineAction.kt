package eu.tutorials.whopays.presentation.screen.slotmachine

import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList

sealed interface SlotMachineAction {
    data object OnSpinButtonClick : SlotMachineAction
    data class OnResultClick(val history: ImmutableList<SlotResult>) : SlotMachineAction
    data class UpdateNumberOne(val number: String) : SlotMachineAction
    data class UpdateNumberTwo(val number: String) : SlotMachineAction
    data class UpdateOperator(val operator: String) : SlotMachineAction
}