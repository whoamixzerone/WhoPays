package eu.tutorials.whopays.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import eu.tutorials.whopays.presentation.screen.round.RoundRoute
import eu.tutorials.whopays.presentation.screen.scoreboard.ScoreBoardRoute
import eu.tutorials.whopays.presentation.screen.slotmachine.SlotMachineRoute

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Route.Round)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Route.Round> {
                RoundRoute(onNavigateToSlotMachine = { round ->
                    backStack.clear()
                    backStack.add(Route.SlotMachine(round))
                })
            }
            entry<Route.SlotMachine> { key ->
                SlotMachineRoute(
                    round = key.round,
                    onNavigateToResult = { history ->
                        backStack.removeIf { navKey ->
                            navKey is Route.ScoreBoard
                        }
                        backStack.add(Route.ScoreBoard(history))
                    }
                )
            }
            entry<Route.ScoreBoard> { key ->
                ScoreBoardRoute(key.history)
            }
        }
    )
}
