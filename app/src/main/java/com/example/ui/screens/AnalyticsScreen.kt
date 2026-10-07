package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.db.ArticleStatsSummary
import com.example.data.db.MistakeRecord
import com.example.data.db.OverallStatsSummary
import com.example.data.db.QuizSessionRecord
import com.example.data.model.GameMode
import com.example.data.model.GermanArticle
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val overallStats by viewModel.overallStats.collectAsState()
    val articleStats by viewModel.articleStats.collectAsState()
    val sessions by viewModel.recentSessions.collectAsState()
    val mistakes by viewModel.allMistakes.collectAsState()

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Genel Analiz", "Artikel Hakimiyeti", "Hatalar Defteri", "Oyun Geçmişi")

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Skor & Analiz Tablosu",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(AppScreen.HOME) },
                        modifier = Modifier.testTag("analytics_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Tab Row
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    )
                }
            }

            when (selectedTab) {
                0 -> GeneralAnalyticsTab(overallStats = overallStats, articleStats = articleStats, sessionsCount = sessions.size)
                1 -> ArticleMasteryTab(articleStats = articleStats)
                2 -> MistakesNotebookTab(
                    mistakes = mistakes,
                    onStartReview = { viewModel.startQuiz(GameMode.MISTAKES_REVIEW) },
                    onDeleteMistake = { viewModel.deleteMistakeWord(it) },
                    onClearAll = { viewModel.clearAllMistakes() }
                )
                3 -> MatchHistoryTab(sessions = sessions)
            }
        }
    }
}

@Composable
private fun GeneralAnalyticsTab(
    overallStats: OverallStatsSummary,
    articleStats: ArticleStatsSummary,
    sessionsCount: Int
) {
    val accuracy = if (overallStats.totalQuestions > 0) {
        ((overallStats.totalCorrect.toFloat() / overallStats.totalQuestions) * 100).toInt()
    } else 0

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // High Level Metric Cards
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Genel Başarı Tablosu",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.QueryStats,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        MetricCard(
                            label = "Toplam Skor",
                            value = "${overallStats.totalScore}",
                            color = GoldAccent,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        MetricCard(
                            label = "En Yüksek Skor",
                            value = "${overallStats.highestScore}",
                            color = PrimaryIndigo,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        MetricCard(
                            label = "Oynanan Oyun",
                            value = "${overallStats.totalGames}",
                            color = Color(0xFF0D9488),
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        MetricCard(
                            label = "Genel Doğruluk",
                            value = "%$accuracy",
                            color = SuccessGreen,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Quick Article Summary Bar
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Artikel Başarı Özeti",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    ArticleProgressBar(
                        article = "der",
                        correct = articleStats.totalDerCorrect,
                        total = articleStats.totalDerAttempted,
                        color = ArticleDer
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ArticleProgressBar(
                        article = "die",
                        correct = articleStats.totalDieCorrect,
                        total = articleStats.totalDieAttempted,
                        color = ArticleDie
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ArticleProgressBar(
                        article = "das",
                        correct = articleStats.totalDasCorrect,
                        total = articleStats.totalDasAttempted,
                        color = ArticleDas
                    )
                }
            }
        }

        // Motivation & Rank Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(
                            text = if (accuracy >= 80) "Wort-Meister Seviyesi!" else "Öğrenme Yolculuğu Sürüyor",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Düzenli oyun oynayarak artikelleri kalıcı hafızana yerleştir.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ArticleMasteryTab(articleStats: ArticleStatsSummary) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Kapsamlı Artikel Analizi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Hangi artikellerde daha başarılı olduğunu analiz et.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            ArticleDetailCard(
                article = "der (Maskulin)",
                correct = articleStats.totalDerCorrect,
                total = articleStats.totalDerAttempted,
                color = ArticleDer,
                tip = "İpucu: Günler, aylar, meslek isimleri ve erkek aile bireyleri (der Vater, der Bruder) genellikle 'der'dir."
            )
        }

        item {
            ArticleDetailCard(
                article = "die (Feminin)",
                correct = articleStats.totalDieCorrect,
                total = articleStats.totalDieAttempted,
                color = ArticleDie,
                tip = "İpucu: Kadın aile bireyleri (die Mutter, die Schwester), -in, -e, -ung ile bitenler genellikle 'die'dir."
            )
        }

        item {
            ArticleDetailCard(
                article = "das (Neutrum)",
                correct = articleStats.totalDasCorrect,
                total = articleStats.totalDasAttempted,
                color = ArticleDas,
                tip = "İpucu: -chen küçültme ekleri (das Brötchen, das Würstchen), metaller ve yabancı kökenli kelimeler genelde 'das'tır."
            )
        }
    }
}

@Composable
private fun ArticleDetailCard(
    article: String,
    correct: Int,
    total: Int,
    color: Color,
    tip: String
) {
    val rate = if (total > 0) ((correct.toFloat() / total) * 100).toInt() else 0

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = color
                )
                Text(
                    text = "%$rate Başarı",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 17.sp,
                    color = color
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = { if (total > 0) correct.toFloat() / total else 0f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = color,
                trackColor = color.copy(alpha = 0.15f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$correct Doğru / $total Toplam",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                color = color.copy(alpha = 0.08f),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = tip,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
private fun MistakesNotebookTab(
    mistakes: List<MistakeRecord>,
    onStartReview: () -> Unit,
    onDeleteMistake: (String) -> Unit,
    onClearAll: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hatalı Kelimeler Defteri",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${mistakes.size} kelimede hata kaydedildi",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (mistakes.isNotEmpty()) {
                    IconButton(onClick = onClearAll) {
                        Icon(
                            imageVector = Icons.Default.DeleteSweep,
                            contentDescription = "Tümünü Temizle",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        if (mistakes.isNotEmpty()) {
            item {
                Button(
                    onClick = onStartReview,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("start_mistake_review_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                ) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Bu Kelimelerle Alıştırma Yap (${mistakes.size})",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (mistakes.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = GoldAccent,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Harika! Henüz kaydedilmiş hata yok.",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Oyun oynadıkça yanlış yaptığın kelimeler burada toplanır.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        } else {
            items(mistakes) { mistake ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                ArticleTag(article = GermanArticle.fromString(mistake.article))
                                Text(
                                    text = mistake.german,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "🇹🇷 ${mistake.turkish}",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (mistake.exampleSentence.isNotBlank()) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "\"${mistake.exampleSentence}\"",
                                    fontSize = 11.sp,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = ErrorRed.copy(alpha = 0.12f)
                        ) {
                            Text(
                                text = "${mistake.mistakeCount} Hata",
                                color = ErrorRed,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MatchHistoryTab(sessions: List<QuizSessionRecord>) {
    val dateFormat = remember { SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Son Oyunlar Geçmişi",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Geçmiş oyunların ve aldığın puanlar",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (sessions.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Henüz oyun geçmişi bulunmuyor.",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        } else {
            items(sessions) { session ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = session.mode,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = dateFormat.format(Date(session.timestamp)),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${session.correctCount} / ${session.totalQuestions} Doğru",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryIndigo
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${session.score} Puan",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = GoldAccent
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (session.accuracyRate >= 80) SuccessGreen.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = "%${session.accuracyRate.toInt()}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = if (session.accuracyRate >= 80) SuccessGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = color.copy(alpha = 0.1f),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = color
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ArticleProgressBar(
    article: String,
    correct: Int,
    total: Int,
    color: Color
) {
    val rate = if (total > 0) ((correct.toFloat() / total) * 100).toInt() else 0

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = color
            )
            Text(
                text = "%$rate ($correct/$total)",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { if (total > 0) correct.toFloat() / total else 0f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = color.copy(alpha = 0.15f)
        )
    }
}
