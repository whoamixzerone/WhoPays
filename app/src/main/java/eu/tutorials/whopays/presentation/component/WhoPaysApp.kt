package eu.tutorials.whopays.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tutorials.whopays.data.model.SlotResult
import eu.tutorials.whopays.data.model.SlotViewModel
import eu.tutorials.whopays.presentation.Screen

@Composable
fun WhoPaysApp(navController: NavHostController, slotViewModel: SlotViewModel, modifier: Modifier = Modifier) {
    val slotState by slotViewModel.slotState

    val numbers = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val operators = listOf("+", "-", "*")

    NavHost(navController = navController, startDestination = Screen.RoundScreen.route) {
        composable(route = Screen.RoundScreen.route) {
            RoundScreen(modifier = modifier) {
                navController.currentBackStackEntry?.savedStateHandle?.set("round", it)
                navController.navigate(Screen.SlotMachineScreen.route)
            }
        }

        composable(route = Screen.SlotMachineScreen.route) {
            val round = navController.previousBackStackEntry?.savedStateHandle?.get("round") ?: 0

            SlotMachineScreen(
                slotState = slotState.copy(totalRound = round),
                numbers = numbers,
                operators = operators,
                modifier = modifier,
                onSpinClick = slotViewModel::startSpinning,
                onStopClick = slotViewModel::stopSpinning,
                onNumber1Change = slotViewModel::updateNumber1,
                onOperatorChange = slotViewModel::updateOperator,
                onNumber2Change = slotViewModel::updateNumber2
            ) {
                navController.currentBackStackEntry?.savedStateHandle?.set("slotResults", it)
                navController.navigate(Screen.ScoreBoardScreen.route)
            }
        }

        composable(route = Screen.ScoreBoardScreen.route) {
            val slotResults = navController.previousBackStackEntry?.savedStateHandle?.get("slotResults")
                ?: emptyList<SlotResult>()

            ScoreBoardScreen(slotResults, modifier)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WhoPaysAppPreview() {
    val navController = rememberNavController()
    val slotViewModel: SlotViewModel = viewModel()

    Scaffold { innerPadding ->
        WhoPaysApp(navController, slotViewModel, Modifier.padding(innerPadding))
    }
}