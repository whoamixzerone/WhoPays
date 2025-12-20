package eu.tutorials.whopays.data.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SlotViewModel : ViewModel() {
    private val _slotState = mutableStateOf(SlotState())
    val slotState: State<SlotState> = _slotState

    fun updateNumber1(value: String) {
        _slotState.value = _slotState.value.copy(number1 = value)
    }

    fun updateOperator(value: String) {
        _slotState.value = _slotState.value.copy(operator = value)
    }

    fun updateNumber2(value: String) {
        _slotState.value = _slotState.value.copy(number2 = value)
    }

    fun startSpinning() {
        _slotState.value = _slotState.value.copy(isSpinning = true)
    }

    fun stopSpinning() {
        _slotState.value = _slotState.value.copy(isSpinning = false)

        viewModelScope.launch {
            delay(100)
            generateSlotResult()
        }
    }

    private fun generateSlotResult() {
        val state = _slotState.value
        val number1 = state.number1.toIntOrNull() ?: 0
        val number2 = state.number2.toIntOrNull() ?: 0
        val operator = state.operator

        val result = SlotResult(
            number1 = number1,
            operator = operator,
            number2 = number2,
            result = calculateResult(number1, operator, number2)
        )

        _slotState.value = _slotState.value.copy(
            round = _slotState.value.round + 1,
            history = _slotState.value.history + result
        )
    }

    private fun calculateResult(number1: Int, operator: String, number2: Int): Int =
        when (operator) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            else -> 0
        }
}