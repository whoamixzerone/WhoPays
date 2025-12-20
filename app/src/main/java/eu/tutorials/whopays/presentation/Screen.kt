package eu.tutorials.whopays.presentation

sealed class Screen(val route: String) {
    object RoundScreen : Screen("round")
    object SlotMachineScreen : Screen("slotmachine")
    object ScoreBoardScreen : Screen("scoreboard")
}