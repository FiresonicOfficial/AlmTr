package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AnswerEvaluation
import com.example.ui.components.ArticleTag
import com.example.ui.theme.ArticleDas
import com.example.ui.theme.ArticleDer
import com.example.ui.theme.ArticleDie
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

@Composable
fun GameResultScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.gameState.collectAsState()
    val total = state.questions.size
    val correct = state.answers.count { it.isCorrect }
    val accuracy = if (total > 0) (correct.toFloat() / total * 100).toInt() else 0

    val (title, color) = when {
        accuracy >= 90 -> Pair("Kusursuz! WortMeister!", GoldAccent)
        accuracy >= 70 -> Pair("Tebrikler! Çok Başarılı!", SuccessGreen)
        accuracy >= 50 -> Pair("Güzel Gelişme!", PrimaryIndigo)
        else -> Pair("Tekrar Et ve Güçlen!", Color(0xFFEA580C))
    }

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .testTag("game_result_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Score Trophy Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(color.copy(alpha = 0.18f), Color.Transparent)
                            )
                        )
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Surface(
                            shape = CircleShape,
                            color = color.copy(alpha = 0.2f),
                            modifier = Modifier.size(68.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = null,
                                    tint = color,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "${state.mode.title} Modu Tamamlandı",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        // Stats Grid
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ResultStatBox(
                                label = "Puan",
                                value = "${state.score}",
                                iconColor = GoldAccent
                            )
                            ResultStatBox(
                                label = "Doğru",
                                value = "$correct / $total",
                                iconColor = SuccessGreen
                            )
                            ResultStatBox(
                                label = "Başarı",
                                value = "%$accuracy",
                                iconColor = PrimaryIndigo
                            )
                            ResultStatBox(
                                label = "En İyi Seri",
                                value = "${state.maxStreak}x",
                                iconColor = GoldAccent
                            )
                        }
                    }
                }
            }
        }

        // Article Performance Mini Banner (if articles were in quiz)
        val totalArticleQuestions = state.derTotal + state.dieTotal + state.dasTotal
        if (totalArticleQuestions > 0) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Bu Oyundaki Artikel Başarısı",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ArticleScorePill("der", state.derCorrect, state.derTotal, ArticleDer)
                            ArticleScorePill("die", state.dieCorrect, state.dieTotal, ArticleDie)
                            ArticleScorePill("das", state.dasCorrect, state.dasTotal, ArticleDas)
                        }
                    }
                }
            }
        }

        // Section Title: Detaylı Soru Analizi
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Soru Analiz Tablosu",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$correct Doğru, ${total - correct} Yanlış",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Question-by-question breakdown list
        itemsIndexed(state.answers) { index, eval ->
            AnswerReviewCard(index = index + 1, evaluation = eval)
        }

        // Action Buttons
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = { viewModel.startQuiz(state.mode) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("play_again_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Tekrar Oyna",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Button(
                    onClick = { viewModel.navigateTo(AppScreen.ANALYTICS) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("open_scoreboard_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Icon(imageVector = Icons.Default.Analytics, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Kapsamlı Skor ve Analiz Tablosu",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                OutlinedButton(
                    onClick = { viewModel.navigateTo(AppScreen.HOME) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("back_to_home_button"),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Ana Menüye Dön", fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ResultStatBox(
    label: String,
    value: String,
    iconColor: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ArticleScorePill(
    name: String,
    correct: Int,
    total: Int,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = color.copy(alpha = 0.12f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$name: ",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = color
            )
            Text(
                text = if (total > 0) "$correct/$total" else "-",
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun AnswerReviewCard(
    index: Int,
    evaluation: AnswerEvaluation
) {
    val isCorrect = evaluation.isCorrect
    val indicatorColor = if (isCorrect) SuccessGreen else ErrorRed

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Number indicator with icon
            Surface(
                shape = CircleShape,
                color = indicatorColor.copy(alpha = 0.15f),
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                        contentDescription = null,
                        tint = indicatorColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ArticleTag(article = evaluation.word.article)
                    Text(
                        text = evaluation.word.german,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "🇹🇷 ${evaluation.word.turkish}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (!isCorrect) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = evaluation.explanation,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = ErrorRed
                    )
                }
            }
        }
    }
}
