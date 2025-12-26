package eu.tutorials.whopays.presentation.screen.round

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoundViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RoundState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RoundEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onAction(action: RoundAction) {
        when (action) {
            is RoundAction.OnStartGameClick -> emitEffect(
                RoundEffect.NavigateToSlotMachine(action.round)
            )
            is RoundAction.InputRound -> setRound(action.roundText)
        }
    }

    private fun setRound(roundText: String) {
        val roundString = roundText.filter { it.isDigit() }

        _uiState.update {
            it.copy(
                roundText = roundString,
                round = roundString.toIntOrNull() ?: 0
            )
        }
    }

    private fun emitEffect(effect: RoundEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}
