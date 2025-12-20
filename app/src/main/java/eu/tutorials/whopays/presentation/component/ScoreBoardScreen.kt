package eu.tutorials.whopays.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.whopays.data.model.SlotResult

@Composable
fun ScoreBoardScreen(slotResults: List<SlotResult>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF581C87),
                            Color(0xFF3730A3),
                            Color(0xFF1E3A8A)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(4.dp, Color(0xFFFFC140), RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "✨ Score Board ✨",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = LocalTextStyle.current.copy(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFFDE047),
                                Color(0xFFF9A8D4),
                                Color(0xFFC084FC)
                            )
                        )
                    )
                )
                Spacer(Modifier.height(30.dp))

                if (slotResults.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        itemsIndexed(slotResults) { index, slotResult ->
                            ResultItem(slotResult = slotResult, position = index + 1)
                        }
                    }
                    Spacer(Modifier.height(8.dp))

                    HorizontalDivider(color = Color(0x80FBBF24), thickness = 4.dp)
                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatCard(
                            value = slotResults.size.toString(),
                            emoji = "🎮",
                            label = "게임 수",
                            color = Color(0xFF10B981),
                            modifier = Modifier.weight(1f)
                        )
                        StatCard(
                            value = slotResults.maxOfOrNull { it.result }?.toString() ?: "0",
                            emoji = "🏆",
                            label = "최고",
                            color = Color(0xFF0EA5E9),
                            modifier = Modifier.weight(1f)
                        )
                        StatCard(
                            value = slotResults.minOfOrNull { it.result }?.toString() ?: "0",
                            emoji = "😭",
                            label = "꼴등(당첨)",
                            color = Color(0xFFF43F5E),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ResultItem(slotResult: SlotResult, position: Int) {
    Row(
        modifier = Modifier
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

@Composable
fun StatCard(
    value: String,
    emoji: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                color.copy(alpha = 0.2f),
                RoundedCornerShape(16.dp)
            )
            .border(3.dp, color.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = emoji, fontSize = 26.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = color.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 28.sp,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScoreBoardScreenPreview() {
    Scaffold { innerPadding ->
        ScoreBoardScreen(
            modifier = Modifier.padding(innerPadding),
            slotResults = listOf(
                SlotResult(1, "+", 2, 3),
                SlotResult(1, "*", 2, 2)
            )
        )
    }
}