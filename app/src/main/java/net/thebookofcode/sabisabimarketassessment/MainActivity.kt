package net.thebookofcode.sabisabimarketassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.thebookofcode.sabisabimarketassessment.ui.screens.home.HomeScreen
import net.thebookofcode.sabisabimarketassessment.ui.theme.SabiSabiMarketAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SabiSabiMarketAssessmentTheme {
                HomeScreen()
            }
        }
    }
}