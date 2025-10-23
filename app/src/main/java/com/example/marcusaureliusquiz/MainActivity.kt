package com.example.marcusaureliusquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marcusaureliusquiz.uiview.HomeScreen
import com.example.marcusaureliusquiz.uiview.QuizScreen
import com.example.marcusaureliusquiz.uiview.ResultScreen
import com.example.marcusaureliusquiz.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarcusAureliusQuizApp()
        }
    }
}

@Composable
fun MarcusAureliusQuizApp() {
    val navController = rememberNavController()
    val quizViewModel: QuizViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        // Home Screen
        composable("home") { HomeScreen(navController) }

        // Quiz Screen
        composable("quiz") { QuizScreen(navController, quizViewModel) }

        // Result Screen
        composable("results/{score}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            ResultScreen(score = score, navController = navController, quizViewModel = quizViewModel)
        }
    }
}
