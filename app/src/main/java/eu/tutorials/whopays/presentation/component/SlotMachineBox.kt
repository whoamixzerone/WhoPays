package eu.tutorials.whopays.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import eu.tutorials.whopays.presentation.screen.slotmachine.SlotMachineAction
import eu.tutorials.whopays.presentation.screen.slotmachine.SlotMachineState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SlotMachineBox(
    uiState: SlotMachineState,
    numbers: ImmutableList<String>,
    operators: ImmutableList<String>,
    modifier: Modifier = Modifier,
    onAction: (SlotMachineAction) -> Unit = {},
) {
    val infiniteTransition = rememberInfiniteTransition("electric")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "electric"
    )

    var boxSize by remember { mutableStateOf(IntSize.Zero) }
    val brush = remember(boxSize, offset) {
        Brush.linearGradient(
            colors = listOf(
                Color.Cyan,
                Color.White,
                Color(0xFF66CCFF),
                Color.White,
                Color.Cyan
            ),
            start = Offset(0f, 0f),
            end = Offset(boxSize.width.toFloat() * offset, boxSize.height.toFloat() * offset)
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { boxSize = it }
                .border(width = 4.dp, shape = RoundedCornerShape(16.dp), brush = brush)
                .padding(horizontal = 10.dp)
                .padding(vertical = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SlotReel(
                    items = numbers,
                    isSpinning = uiState.isSpinning,
                    modifier = Modifier.weight(1f),
                    onCurrentValue = { onAction(SlotMachineAction.updateNumberOne(it)) }
                )

                SlotReel(
                    items = operators,
                    isSpinning = uiState.isSpinning,
                    modifier = Modifier.weight(1f),
                    onCurrentValue = { onAction(SlotMachineAction.updateOperator(it)) }
                )

                SlotReel(
                    items = numbers.reversed().toPersistentList(),
                    isSpinning = uiState.isSpinning,
                    modifier = Modifier.weight(1f),
                    onCurrentValue = { onAction(SlotMachineAction.updateNumberTwo(it)) }
                )
            }
        }

        Text(
            text = "🎰",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 4.dp)
                .zIndex(1f)
        )
        Text(
            text = "💎",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 4.dp)
                .zIndex(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SlotMachineBoxPreview() {
    val numbers = persistentListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val operators = persistentListOf("+", "-", "*")

    SlotMachineBox(
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