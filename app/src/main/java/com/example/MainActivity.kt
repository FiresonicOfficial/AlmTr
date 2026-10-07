package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.AnalyticsScreen
import com.example.ui.screens.ArticleQuizScreen
import com.example.ui.screens.DictionaryScreen
import com.example.ui.screens.GameResultScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MultipleChoiceScreen
import com.example.ui.screens.TranslationScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: QuizViewModel = viewModel()
                val currentScreen by viewModel.currentScreen.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        AppScreen.HOME -> HomeScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.ARTIKEL_QUIZ -> ArticleQuizScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.TRANSLATION_QUIZ -> TranslationScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.MULTI_CHOICE_QUIZ -> MultipleChoiceScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.RESULT -> GameResultScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.ANALYTICS -> AnalyticsScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                        AppScreen.DICTIONARY -> DictionaryScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
