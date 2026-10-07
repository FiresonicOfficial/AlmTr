package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GameMode
import com.example.ui.theme.ArticleDas
import com.example.ui.theme.ArticleDer
import com.example.ui.theme.ArticleDie
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.PrimaryIndigoDark
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

@Composable
fun HomeScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val overallStats by viewModel.overallStats.collectAsState()
    val mistakes by viewModel.allMistakes.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Header Card
        item {
            HeroHeaderCard(
                totalScore = overallStats.totalScore,
                totalGames = overallStats.totalGames,
                onScoreboardClick = { viewModel.navigateTo(AppScreen.ANALYTICS) },
                onDictionaryClick = { viewModel.navigateTo(AppScreen.DICTIONARY) }
            )
        }

        // Section Title: Oyun Modları
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Oyun Modları",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "120+ Ders Kelimesi",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Mode 1: Artikel Ustası
        item {
            GameModeCard(
                title = "Artikel Ustası (der / die / das)",
                subtitle = "Almanca kelimelerin artikelini doğru tahmin et!",
                icon = Icons.Default.Category,
                badgeText = "En Popüler",
                gradient = Brush.horizontalGradient(
                    listOf(Color(0xFF1E3A8A), Color(0xFF2563EB))
                ),
                tag = "mode_artikel",
                onClick = { viewModel.startQuiz(GameMode.ARTIKEL) }
            )
        }

        // Mode 2: Türkçe -> Almanca Yazma & Çeviri (Strict Article Check)
        item {
            GameModeCard(
                title = "Türkçe ➔ Almanca Yaz & Çevir",
                subtitle = "Kelimeyi artikeliyle doğru yaz! Yanlışta doğrusunu gösterir.",
                icon = Icons.Default.Edit,
                badgeText = "Puanlı & İpuçlu",
                gradient = Brush.horizontalGradient(
                    listOf(Color(0xFF701A75), Color(0xFF9333EA))
                ),
                tag = "mode_tr_to_de",
                onClick = { viewModel.startQuiz(GameMode.TR_TO_DE) }
            )
        }

        // Mode 3: Almanca -> Türkçe Çoktan Seçmeli
        item {
            GameModeCard(
                title = "Almanca ➔ Türkçe Anlam Testi",
                subtitle = "Verilen Almanca kelimenin Türkçe karşılığını seç.",
                icon = Icons.Default.Translate,
                badgeText = "Test Modu",
                gradient = Brush.horizontalGradient(
                    listOf(Color(0xFF065F46), Color(0xFF059669))
                ),
                tag = "mode_de_to_tr",
                onClick = { viewModel.startQuiz(GameMode.DE_TO_TR) }
            )
        }

        // Mode 4: Karışık Şampiyona
        item {
            GameModeCard(
                title = "Karışık Şampiyona",
                subtitle = "Artikel, çeviri ve test karma sorularıyla rekor kır!",
                icon = Icons.Default.EmojiEvents,
                badgeText = "Karma",
                gradient = Brush.horizontalGradient(
                    listOf(Color(0xFF9A3412), Color(0xFFEA580C))
                ),
                tag = "mode_championship",
                onClick = { viewModel.startQuiz(GameMode.CHAMPIONSHIP) }
            )
        }

        // Mode 5: Hatalı Kelimeleri Tekrar Et (Only visible if mistakes exist or accessible)
        if (mistakes.isNotEmpty()) {
            item {
                GameModeCard(
                    title = "Hatalı Kelimeleri Tekrar Et (${mistakes.size})",
                    subtitle = "Daha önce yanlış yaptığın kelimelere odaklanıp ustalaş!",
                    icon = Icons.Default.Refresh,
                    badgeText = "Özel Tekrar",
                    gradient = Brush.horizontalGradient(
                        listOf(Color(0xFF991B1B), Color(0xFFDC2626))
                    ),
                    tag = "mode_mistakes",
                    onClick = { viewModel.startQuiz(GameMode.MISTAKES_REVIEW) }
                )
            }
        }

        // Fast Access Bottom Actions
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { viewModel.navigateTo(AppScreen.ANALYTICS) },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("nav_analytics_button"),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Analytics,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Skor Tablosu",
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = { viewModel.navigateTo(AppScreen.DICTIONARY) },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("nav_dictionary_button"),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Kelime Rehberi",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroHeaderCard(
    totalScore: Int,
    totalGames: Int,
    onScoreboardClick: () -> Unit,
    onDictionaryClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("hero_header_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            PrimaryIndigo.copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "WortMeister",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Almanca Kelime & Artikel Oyunu",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Der Die Das Pills
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        ArticlePill(text = "der", color = ArticleDer)
                        ArticlePill(text = "die", color = ArticleDie)
                        ArticlePill(text = "das", color = ArticleDas)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Stats Banner
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StatItem(
                            title = "Toplam Puan",
                            value = "$totalScore",
                            icon = Icons.Default.Star,
                            iconColor = GoldAccent
                        )
                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(32.dp)
                                .background(MaterialTheme.colorScheme.outlineVariant)
                        )
                        StatItem(
                            title = "Oyun Sayısı",
                            value = "$totalGames",
                            icon = Icons.Default.EmojiEvents,
                            iconColor = PrimaryIndigo
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatItem(
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ArticlePill(text: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.15f),
        border = androidx.compose.foundation.BorderStroke(1.dp, color)
    ) {
        Text(
            text = text,
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun GameModeCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    badgeText: String,
    gradient: Brush,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon Box
                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.88f),
                        lineHeight = 17.sp
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Play circle
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Oyna",
                            tint = Color.Black,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }
    }
}
