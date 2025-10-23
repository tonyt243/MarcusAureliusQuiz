package com.example.marcusaureliusquiz.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

class QuizViewModel : ViewModel() {


    private val _questions = listOf(
        Question(
            "1. Who said 'The impediment to action advances action'?",
            listOf("Seneca", "Plato", "Marcus Aurelius", "Cicero"),
            2
        ),
        Question(
            "2. Which Roman emperor wrote Meditations?",
            listOf("Marcus Aurelius", "Nero", "Augustus", "Trajan"),
            0
        ),
        Question(
            "3. Marcus Aurelius was emperor in which century?",
            listOf("2nd century", "1st century", "3rd century", "4th century"),
            0
        ),
        Question(
            "4. What philosophy did Marcus Aurelius follow?",
            listOf("Stoicism", "Epicureanism", "Cynicism", "Platonism"),
            0
        ),
        Question(
            "5. Which war did Marcus Aurelius fight during his reign?",
            listOf("Gallic Wars", "Marcomannic Wars", "Punic Wars", "Persian Wars"),
            1
        ),
        Question(
            "6. Marcus Aurelius considered which practice vital for life?",
            listOf("Meditation", "Wealth accumulation", "Alchemy", "Gladiator games"),
            0
        ),
        Question(
            "7. Marcus Aurelius wrote Meditations in what language?",
            listOf("Greek", "Hebrew", "Italian", "Latin"),
            3
        ),
        Question(
            "8. What was the main theme of *Meditations*?",
            listOf("Self-discipline and virtue", "Military strategy", "Love poetry", "Political reform"),
            0
        ),
        Question(
            "9. Marcus Aurelius belonged to which group of Roman emperors?",
            listOf("Five Good Emperors", "Julio-Claudian Dynasty", "Flavian Dynasty", "Severan Dynasty"),
            0
        ),
        Question(
            "10. Which Stoic principle did Marcus Aurelius emphasize most?",
            listOf("Divine prophecy", "Pursuit of pleasure", "Accumulation of wealth", "Control over emotions"),
            3
        )
    )



    val questions: List<Question> = _questions

    // Track current question index
    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    // Track score
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    fun answerQuestion(selectedIndex: Int) {
        if (selectedIndex == _questions[_currentIndex.value].correctAnswerIndex) {
            _score.value += 1
        }
        if (_currentIndex.value < _questions.size - 1) {
            _currentIndex.value += 1
        }
    }

    fun resetQuiz() {
        _currentIndex.value = 0
        _score.value = 0
    }
}
