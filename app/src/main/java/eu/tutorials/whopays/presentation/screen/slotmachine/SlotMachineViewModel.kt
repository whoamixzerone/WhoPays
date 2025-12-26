package eu.tutorials.whopays.presentation.screen.slotmachine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.whopays.data.model.SlotResult
import eu.tutorials.whopays.presentation.screen.slotmachine.SlotMachineEffect.NavigationToResult
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

    private var isProcessing = false

    fun onAction(action: SlotMachineAction) {
        when (action) {
            is SlotMachineAction.OnResultClick -> emitEffect(NavigationToResult(action.history))
            is SlotMachineAction.UpdateNumberOne -> updateNumberOne(action.number)
            is SlotMachineAction.UpdateNumberTwo -> updateNumberTwo(action.number)
            is SlotMachineAction.UpdateOperator -> updateOperator(action.operator)
            SlotMachineAction.OnSpinButtonClick -> {
                if (isProcessing) return

                isProcessing = true

                val currentStage = _uiState.value.spinStage
                val nextStage = when (currentStage) {
                    SpinStage.IDLE -> SpinStage.SPINNING_ALL
                    SpinStage.SPINNING_ALL -> SpinStage.STOPPED_1
                    SpinStage.STOPPED_1 -> SpinStage.STOPPED_2
                    SpinStage.STOPPED_2 -> SpinStage.IDLE
                }

                updateSpinStage(nextStage)

                if (nextStage == SpinStage.IDLE) {
                    viewModelScope.launch {
                        delay(100)
                        generateSlotResult()

                        delay(300)
                        isProcessing = false
                    }
                } else {
                    viewModelScope.launch {
                        delay(300)
                        isProcessing = false
                    }
                }
            }
        }
    }

    private fun updateNumberOne(number: String) {
        _uiState.update {
            it.copy(number1 = number)
        }
    }

    private fun updateNumberTwo(number: String) {
        _uiState.update {
            it.copy(number2 = number)
        }
    }

    private fun updateOperator(operator: String) {
        _uiState.update {
            it.copy(operator = operator)
        }
    }

    private fun updateSpinStage(nextStage: SpinStage) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    spinStage = nextStage,
                    isButtonEnabled = false
                )
            }

            delay(300)

            _uiState.update {
                it.copy(isButtonEnabled = true)
            }
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
