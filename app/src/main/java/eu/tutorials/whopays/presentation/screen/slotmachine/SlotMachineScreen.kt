package eu.tutorials.whopays.presentation.screen.slotmachine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.whopays.data.model.SlotResult
import eu.tutorials.whopays.presentation.component.RoundIndicator
import eu.tutorials.whopays.presentation.component.SlotMachineBox
import eu.tutorials.whopays.presentation.component.SpinButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SlotMachineScreen(
    uiState: SlotMachineState,
    numbers: ImmutableList<String>,
    operators: ImmutableList<String>,
    modifier: Modifier = Modifier,
    onNumber1Change: (String) -> Unit = {},
    onOperatorChange: (String) -> Unit = {},
    onNumber2Change: (String) -> Unit = {},
    onNavigateScoreBoard: (List<SlotResult>) -> Unit = {},
    onAction: (SlotMachineAction) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundIndicator(
                currentRound = if (uiState.round >= uiState.totalRound) uiState.totalRound else uiState.round,
                totalRounds = uiState.totalRound
            )
            Spacer(Modifier.height(10.dp))

            SlotMachineBox(
                uiState = uiState,
                numbers = numbers,
                operators = operators,
                onNumber1Change = onNumber1Change,
                onOperatorChange = onOperatorChange,
                onNumber2Change = onNumber2Change
            )
            Spacer(Modifier.height(40.dp))

            SpinButton(
                isSpinning = uiState.isSpinning,
                isEnabled = uiState.round <= uiState.totalRound,
                onAction = onAction
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { onAction(SlotMachineAction.OnResultClick(uiState.history)) },
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(25.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF82D4FD)
                ),
                enabled = uiState.round > uiState.totalRound
            ) {
                Text(
                    text = "결과 보기",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SlotMachineScreenPreview() {
    val numbers = persistentListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val operators = persistentListOf("+", "-", "*")

    SlotMachineScreen(
        uiState = SlotMachineState(
            totalRound = 2,
            number1 = "5",
            operator = "+",
            number2 = "3",
            isSpinning = false
        ),
        numbers = numbers,
        operators = operators
    )
}