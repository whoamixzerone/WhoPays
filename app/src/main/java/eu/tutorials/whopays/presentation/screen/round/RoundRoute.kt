package eu.tutorials.whopays.presentation.screen.round

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RoundRoute(
    onNavigateToSlotMachine: (Int) -> Unit = {},
    viewModel: RoundViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is RoundEffect.NavigateToSlotMachine -> onNavigateToSlotMachine(effect.round)
            }
        }
    }

    RoundScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}
