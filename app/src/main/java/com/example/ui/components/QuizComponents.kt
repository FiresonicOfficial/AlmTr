package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AnswerEvaluation
import com.example.data.model.GermanArticle
import com.example.ui.theme.ArticleDas
import com.example.ui.theme.ArticleDasLight
import com.example.ui.theme.ArticleDer
import com.example.ui.theme.ArticleDerLight
import com.example.ui.theme.ArticleDie
import com.example.ui.theme.ArticleDieLight
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.SuccessGreen

@Composable
fun QuizTopBar(
    title: String,
    currentIndex: Int,
    totalQuestions: Int,
    score: Int,
    streak: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.testTag("quiz_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Geri Dön",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Soru ${currentIndex + 1} / $totalQuestions",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }

            // Score and Streak pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Streak badge
                if (streak > 0) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = GoldAccent.copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldAccent)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Seri",
                                tint = GoldAccent,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "${streak}x",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = GoldAccent
                            )
                        }
                    }
                }

                // Score badge
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Puan",
                            tint = GoldAccent,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$score",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { (currentIndex.toFloat()) / totalQuestions.coerceAtLeast(1) },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        )
    }
}

@Composable
fun ArticleTag(
    article: GermanArticle,
    modifier: Modifier = Modifier
) {
    val (bgColor, textColor, label) = when (article) {
        GermanArticle.DER -> Triple(ArticleDerLight, ArticleDer, "der")
        GermanArticle.DIE, GermanArticle.DIE_PLURAL -> Triple(ArticleDieLight, ArticleDie, "die")
        GermanArticle.DAS -> Triple(ArticleDasLight, ArticleDas, "das")
        GermanArticle.DER_DAS -> Triple(ArticleDerLight, ArticleDer, "der/das")
        GermanArticle.NONE -> Triple(Color.LightGray.copy(alpha = 0.3f), Color.DarkGray, "")
    }

    if (label.isNotEmpty()) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = bgColor,
            modifier = modifier
        ) {
            Text(
                text = label,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
    }
}

@Composable
fun AnswerFeedbackCard(
    evaluation: AnswerEvaluation,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSuccess = evaluation.isCorrect
    val containerColor = if (isSuccess) SuccessGreen.copy(alpha = 0.12f) else ErrorRed.copy(alpha = 0.12f)
    val borderColor = if (isSuccess) SuccessGreen else ErrorRed

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("answer_feedback_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (isSuccess) Icons.Default.CheckCircle else Icons.Default.Close,
                    contentDescription = if (isSuccess) "Doğru" else "Yanlış",
                    tint = if (isSuccess) SuccessGreen else ErrorRed,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (isSuccess) "Harika! Doğru Cevap" else "Dikkat! Hatalı Cevap",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = if (isSuccess) SuccessGreen else ErrorRed
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Explanation
            Text(
                text = evaluation.explanation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium
            )

            // Show detail card if wrong
            if (!isSuccess) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Doğru Cevap:",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = evaluation.correctAnswer,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = SuccessGreen
                            )
                        }

                        if (evaluation.userAnswer.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Senin Cevabın:",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = evaluation.userAnswer,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = ErrorRed
                                )
                            }
                        }

                        if (evaluation.word.exampleSentence.isNotBlank()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Örnek: \"${evaluation.word.exampleSentence}\"",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("continue_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSuccess) SuccessGreen else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Devam Et",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun GermanSpecialCharsRow(
    onCharClick: (String) -> Unit,
    onArticleInsert: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Quick article inserters
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            QuickArticleChip(
                article = "der",
                color = ArticleDer,
                onClick = { onArticleInsert("der ") },
                modifier = Modifier.weight(1f)
            )
            QuickArticleChip(
                article = "die",
                color = ArticleDie,
                onClick = { onArticleInsert("die ") },
                modifier = Modifier.weight(1f)
            )
            QuickArticleChip(
                article = "das",
                color = ArticleDas,
                onClick = { onArticleInsert("das ") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Special letters row: ä, ö, ü, ß, Ä, Ö, Ü
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val specialChars = listOf("ä", "ö", "ü", "ß", "Ä", "Ö", "Ü")
            specialChars.forEach { char ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onCharClick(char) }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = char,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickArticleChip(
    article: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = color.copy(alpha = 0.12f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color),
        modifier = modifier
            .height(36.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "+ $article",
                color = color,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}
