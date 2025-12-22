package eu.tutorials.whopays.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundIndicator(
    currentRound: Int,
    totalRounds: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF581C87),
                        Color(0xFF3730A3)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .border(3.dp, Color(0xFFFFC140), RoundedCornerShape(16.dp))
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "🎮",
            fontSize = 24.sp
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Round $currentRound / $totalRounds",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFDE047)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RoundIndicatorPreview() {
    Scaffold { innerPadding ->
        RoundIndicator(1, 3, modifier = Modifier.padding(innerPadding))
    }
}