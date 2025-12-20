package eu.tutorials.whopays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import eu.tutorials.whopays.data.model.SlotViewModel
import eu.tutorials.whopays.presentation.component.WhoPaysApp
import eu.tutorials.whopays.ui.theme.WhopaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val slotViewModel: SlotViewModel = viewModel()

            WhopaysTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WhoPaysApp(
                        navController = navController,
                        slotViewModel = slotViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
