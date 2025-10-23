package com.example.marcusaureliusquiz.uiview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.marcusaureliusquiz.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController, quizViewModel: QuizViewModel = viewModel()) {

    val currentIndex by quizViewModel.currentIndex.collectAsState()
    val score by quizViewModel.score.collectAsState()
    val question = quizViewModel.questions[currentIndex]

    var selectedIndex by remember { mutableStateOf(-1) }
    var showFeedback by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Marcus Aurelius Quiz") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF8B0000))
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF4E1))
                .padding(padding)
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {


                Text(
                    text = "Question ${currentIndex + 1}/${quizViewModel.questions.size}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Question text
                Text(
                    text = question.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))


                question.options.forEachIndexed { index, option ->
                    val buttonColor = when {
                        showFeedback && index == question.correctAnswerIndex -> Color(0xFF228B22)
                        showFeedback && index == selectedIndex && index != question.correctAnswerIndex -> Color(0xFFB22222)
                        else -> Color(0xFFD2691E)
                    }

                    Button(
                        onClick = {
                            if (!showFeedback) {
                                selectedIndex = index
                                showFeedback = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(option, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                if (showFeedback) {
                    Button(
                        onClick = {

                            if (selectedIndex == question.correctAnswerIndex) {
                                quizViewModel.answerQuestion(selectedIndex)
                            } else {
                                quizViewModel.answerQuestion(-1)
                            }


                            if (currentIndex == quizViewModel.questions.size - 1) {
                                navController.navigate(
                                    "results/${score + if (selectedIndex == question.correctAnswerIndex) 1 else 0}"
                                )
                            }


                            selectedIndex = -1
                            showFeedback = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C00)), // deep orange
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = if (currentIndex == quizViewModel.questions.size - 1) "Finish Quiz" else "Next Question",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
