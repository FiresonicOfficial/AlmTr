package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AnswerFeedbackCard
import com.example.ui.components.QuizTopBar
import com.example.ui.theme.ArticleDas
import com.example.ui.theme.ArticleDasLight
import com.example.ui.theme.ArticleDer
import com.example.ui.theme.ArticleDerLight
import com.example.ui.theme.ArticleDie
import com.example.ui.theme.ArticleDieLight
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

@Composable
fun ArticleQuizScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.gameState.collectAsState()
    val question = state.currentQuestion

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    if (question == null) return

    Scaffold(
        topBar = {
            QuizTopBar(
                title = "Artikel Ustası",
                currentIndex = state.currentIndex,
                totalQuestions = state.questions.size,
                score = state.score,
                streak = state.streak,
                onBackClick = { viewModel.navigateTo(AppScreen.HOME) }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Category Chip
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = question.word.category.titleTr,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Big Word Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("word_prompt_card"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Hangi artikel?",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = question.prompt,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )

                        if (question.word.plural.isNotBlank() && question.word.plural != "-") {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Çoğul: die ${question.word.plural}",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Turkish Meaning
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "🇹🇷 ${question.hint}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Three Big Article Buttons
                if (!state.isAnswerSubmitted) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        ArticleSelectButton(
                            articleName = "der",
                            description = "Eril (Maskulin)",
                            color = ArticleDer,
                            lightColor = ArticleDerLight,
                            tag = "article_der_button",
                            onClick = { viewModel.submitArticleAnswer("der") }
                        )

                        ArticleSelectButton(
                            articleName = "die",
                            description = "Dişil (Feminin)",
                            color = ArticleDie,
                            lightColor = ArticleDieLight,
                            tag = "article_die_button",
                            onClick = { viewModel.submitArticleAnswer("die") }
                        )

                        ArticleSelectButton(
                            articleName = "das",
                            description = "Nötr (Neutrum)",
                            color = ArticleDas,
                            lightColor = ArticleDasLight,
                            tag = "article_das_button",
                            onClick = { viewModel.submitArticleAnswer("das") }
                        )
                    }
                }
            }

            // Answer feedback card if submitted
            if (state.isAnswerSubmitted && state.lastEvaluation != null) {
                Spacer(modifier = Modifier.height(16.dp))
                AnswerFeedbackCard(
                    evaluation = state.lastEvaluation!!,
                    onContinueClick = { viewModel.proceedToNextQuestion() }
                )
            }
        }
    }
}

@Composable
private fun ArticleSelectButton(
    articleName: String,
    description: String,
    color: Color,
    lightColor: Color,
    tag: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = lightColor.copy(alpha = 0.45f),
        border = androidx.compose.foundation.BorderStroke(2.dp, color),
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .testTag(tag)
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = articleName,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )

            Text(
                text = description,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = color.copy(alpha = 0.85f)
            )
        }
    }
}
