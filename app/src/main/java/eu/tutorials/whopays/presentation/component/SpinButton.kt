package eu.tutorials.whopays.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.whopays.data.model.SlotState
import kotlinx.coroutines.delay

@Composable
fun SpinButton(
    modifier: Modifier = Modifier,
    isSpinning: Boolean = false,
    isEnabled: Boolean = false,
    onSpinClick: () -> Unit = {},
    onStopClick: () -> Unit = {}
) {
    var buttonPressed by remember { mutableStateOf(false) }

    val rotation by rememberInfiniteTransition("rotation")
        .animateFloat(
            initialValue = 0f,
            targetValue = if (isSpinning) 360f else 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "rotation"
        )

    LaunchedEffect(buttonPressed) {
        if (buttonPressed) {
            delay(100)
            buttonPressed = false
        }
    }

    Button(
        onClick = {
            if (!isSpinning) {
                onSpinClick()
                buttonPressed = true
            } else {
                onStopClick()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .scale(if (buttonPressed) 0.95f else 1f)
            .shadow(25.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF82D4FD)
        ),
        enabled = isEnabled
    ) {
        Text(
            text = "🎯",
            modifier = Modifier.rotate(rotation),
            fontSize = 42.sp
        )
        Spacer(Modifier.width(30.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isSpinning) "STOP" else "SPIN",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "터치하세요! 👆",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SpinButtonPreview() {
    SpinButton(
        isSpinning = SlotState().isSpinning
    )
}