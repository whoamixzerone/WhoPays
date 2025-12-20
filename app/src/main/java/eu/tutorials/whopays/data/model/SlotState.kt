package eu.tutorials.whopays.data.model

data class SlotState(
    val round: Int = 1,
    val totalRound: Int = 1,
    val number1: String = "0",
    val operator: String = "+",
    val number2: String = "0",
    val isSpinning: Boolean = false,
    val history: List<SlotResult> = emptyList()
)
