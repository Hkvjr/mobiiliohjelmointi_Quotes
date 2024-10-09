package com.example.quotes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quotes.ui.theme.QuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesTheme { //käytetään sovelluksen teemoja
                Surface(
                    modifier = Modifier.fillMaxSize(), //täyttää koko näytön
                    color = MaterialTheme.colorScheme.background //taustavärin asettaminen
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") { //määritetään navigointipolku
        composable("main") { // päänäytön määrittäminen
            MainScreen(onNavigateToInfo = { navController.navigate("info") })
        }
        composable("info") { //info näytön määrittäminen
            InfoScreen(onNavigateBack = { navController.popBackStack() }) //palataan takaisin päänäyttöön
        }
    }
}