package eu.tutorials.whopays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import eu.tutorials.whopays.navigation.AppNavGraph
import eu.tutorials.whopays.ui.theme.WhopaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhopaysTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    WhoPaysApp(modifier = Modifier.padding(innerPadding))
                    AppNavGraph(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
