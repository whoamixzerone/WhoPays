package eu.tutorials.whopays.presentation.screen.slotmachine

import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SlotMachineState(
    val round: Int = 1,
    val totalRound: Int = 1,
    val number1: String = "0",
    val operator: String = "+",
    val number2: String = "0",
    val isSpinning: Boolean = false,
    val history: ImmutableList<SlotResult> = persistentListOf()
)
