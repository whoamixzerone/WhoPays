package eu.tutorials.whopays.presentation.screen.slotmachine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SlotMachineViewModel(round: Int) : ViewModel() {
    private val _uiState =
        MutableStateFlow(SlotMachineState(totalRound = round))
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<SlotMachineEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onAction(action: SlotMachineAction) {
        when (action) {
            SlotMachineAction.OnSpinClick -> startSpinning()
            SlotMachineAction.OnStopClick -> stopSpinning()
            is SlotMachineAction.OnResultClick -> emitEffect(SlotMachineEffect.NavigationToResult(action.history))
        }
    }

    private fun startSpinning() {
        _uiState.update {
            it.copy(isSpinning = true)
        }
    }

    private fun stopSpinning() {
        _uiState.update {
            it.copy(isSpinning = false)
        }

        viewModelScope.launch {
            delay(100)
            generateSlotResult()
        }
    }

    private fun emitEffect(effect: SlotMachineEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    private fun generateSlotResult() {
        val state = _uiState.value
        val number1 = state.number1.toIntOrNull() ?: 0
        val number2 = state.number2.toIntOrNull() ?: 0
        val operator = state.operator

        val result = SlotResult(
            number1 = number1,
            operator = operator,
            number2 = number2,
            result = calculateResult(number1, operator, number2)
        )

        _uiState.update {
            val newHistory = it.history
                .toPersistentList()
                .add(result)

            it.copy(
                round = it.round + 1,
                history = newHistory
            )
        }
    }

    private fun calculateResult(number1: Int, operator: String, number2: Int): Int =
        when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            else -> 0
        }
}