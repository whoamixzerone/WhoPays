package eu.tutorials.whopays.presentation.screen.round

sealed interface RoundAction {
    data class OnStartGameClick(val round: Int) : RoundAction
    data class InputRound(val roundText: String) : RoundAction
}