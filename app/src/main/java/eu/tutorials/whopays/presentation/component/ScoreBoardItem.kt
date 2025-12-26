package eu.tutorials.whopays.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.whopays.data.model.SlotResult

@Composable
fun ScoreBoardItem(
    slotResult: SlotResult,
    position: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xCCA855F7),
                        Color(0xCCDB2777)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .border(3.dp, Color(0xB9FBBF24), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "# $position",
                fontSize = 16.sp,
                color = Color(0xFFFDE047)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${slotResult.number1} ${slotResult.operator} ${slotResult.number2} = ${slotResult.result}",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        val bgColor = when {
            slotResult.result >= 40 -> Brush.horizontalGradient(
                listOf(Color(0xFF10B981), Color(0xFF059669))
            )

            slotResult.result >= 0 -> Brush.horizontalGradient(
                listOf(Color(0xFF3B82F6), Color(0xFF0EA5E9))
            )

            else -> Brush.horizontalGradient(
                listOf(Color(0xFFEF4444), Color(0xFFF43F5E))
            )
        }

        Box(
            modifier = Modifier
                .background(bgColor, RoundedCornerShape(12.dp))
                .border(3.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = slotResult.result.toString(),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScoreBoardItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScoreBoardItem(
            slotResult = SlotResult(1, "+", 2, 3),
            position = 1
        )
    }
}
