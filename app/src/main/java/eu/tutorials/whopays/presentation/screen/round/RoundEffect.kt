package eu.tutorials.whopays.presentation.screen.round

sealed interface RoundEffect {
    data class NavigateToSlotMachine(val round: Int) : RoundEffect
}