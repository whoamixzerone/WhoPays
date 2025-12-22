package eu.tutorials.whopays.presentation.screen.slotmachine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SlotMachineRoute(
    round: Int,
    onNavigateToResult: (ImmutableList<SlotResult>) -> Unit = {},
    viewModel: SlotMachineViewModel = koinViewModel { parametersOf(round) }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val numbers = persistentListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val operators = persistentListOf("+", "-", "*")

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is SlotMachineEffect.NavigationToResult -> onNavigateToResult(effect.history)
            }
        }
    }

    SlotMachineScreen(
        uiState = uiState,
        numbers = numbers,
        operators = operators,
        onAction = viewModel::onAction
    )
}