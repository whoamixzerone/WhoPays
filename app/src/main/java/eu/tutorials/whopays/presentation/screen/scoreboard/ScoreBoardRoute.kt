package eu.tutorials.whopays.presentation.screen.scoreboard

import androidx.compose.runtime.Composable
import eu.tutorials.whopays.data.model.SlotResult
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ScoreBoardRoute(
    history: ImmutableList<SlotResult>,
) {

    ScoreBoardScreen(
        slotResults = history
    )
}