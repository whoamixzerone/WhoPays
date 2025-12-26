package eu.tutorials.whopays.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun SlotReel(
    items: ImmutableList<String>,
    modifier: Modifier = Modifier,
    isSpinning: Boolean = false,
    onCurrentValue: (String) -> Unit = {},
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var displayValue by remember { mutableStateOf(items[0]) }

    val scope = rememberCoroutineScope()
    var spinJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(Unit) {
        onCurrentValue(displayValue)
    }

    LaunchedEffect(isSpinning) {
        if (isSpinning) {
            spinJob = scope.launch {
                while (isActive) {
                    currentIndex = (currentIndex + 1) % items.size
                    displayValue = items[currentIndex]
                    delay(50)
                }
            }
        } else {
            spinJob?.cancel()
            spinJob = null
            onCurrentValue(displayValue)
        }
    }

    Box(
        modifier = modifier
            .aspectRatio(2f / 3f)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5E6EE),
                        Color(0xFFBBB9F8),
                        Color(0xFF93C5FD)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .border(4.dp, Color.White, RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .shadow(elevation = 12.dp, shape = RoundedCornerShape(8.dp), clip = false)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF9AD0FF), Color(0xFF4A8CFF))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.30f),
                                Color.Transparent
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = displayValue,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(6.dp),
                    fontSize = 36.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SlotReelPreview() {
    val items = persistentListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val operations = persistentListOf("+", "-", "*")

    Row {
        SlotReel(
            modifier = Modifier.weight(1f),
            items = items,
            isSpinning = true
        )
        SlotReel(
            modifier = Modifier.weight(1f),
            items = operations
        )
        SlotReel(
            modifier = Modifier.weight(1f),
            items = items
        )
    }
}
