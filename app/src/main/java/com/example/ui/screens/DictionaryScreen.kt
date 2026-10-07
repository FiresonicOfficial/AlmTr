package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GermanArticle
import com.example.data.model.WordCategory
import com.example.data.model.WordItem
import com.example.data.repository.VocabularyData
import com.example.ui.components.ArticleTag
import com.example.ui.theme.ArticleDas
import com.example.ui.theme.ArticleDer
import com.example.ui.theme.ArticleDie
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<WordCategory?>(null) }
    var selectedArticle by remember { mutableStateOf<GermanArticle?>(null) }

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    val filteredWords = remember(searchQuery, selectedCategory, selectedArticle) {
        VocabularyData.allWords.filter { word ->
            val matchesSearch = searchQuery.isBlank() ||
                    word.german.contains(searchQuery, ignoreCase = true) ||
                    word.turkish.contains(searchQuery, ignoreCase = true)
            val matchesCategory = selectedCategory == null || word.category == selectedCategory
            val matchesArticle = selectedArticle == null || word.article == selectedArticle
            matchesSearch && matchesCategory && matchesArticle
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kelime Rehberi (${filteredWords.size})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(AppScreen.HOME) },
                        modifier = Modifier.testTag("dictionary_back_button")
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
            // Search Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("dictionary_search_field"),
                placeholder = { Text("Kelime ara (Almanca veya Türkçe)...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Temizle")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp)
            )

            // Category Filter Chips
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text("Tüm Konular") }
                    )
                }
                items(WordCategory.entries.toTypedArray()) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = {
                            selectedCategory = if (selectedCategory == cat) null else cat
                        },
                        label = { Text(cat.titleTr) }
                    )
                }
            }

            // Article Filter Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedArticle == null,
                        onClick = { selectedArticle = null },
                        label = { Text("Tüm Artikeller") }
                    )
                }
                item {
                    FilterChip(
                        selected = selectedArticle == GermanArticle.DER,
                        onClick = {
                            selectedArticle = if (selectedArticle == GermanArticle.DER) null else GermanArticle.DER
                        },
                        label = { Text("der") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ArticleDer.copy(alpha = 0.2f),
                            selectedLabelColor = ArticleDer
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = selectedArticle == GermanArticle.DIE,
                        onClick = {
                            selectedArticle = if (selectedArticle == GermanArticle.DIE) null else GermanArticle.DIE
                        },
                        label = { Text("die") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ArticleDie.copy(alpha = 0.2f),
                            selectedLabelColor = ArticleDie
                        )
                    )
                }
                item {
                    FilterChip(
                        selected = selectedArticle == GermanArticle.DAS,
                        onClick = {
                            selectedArticle = if (selectedArticle == GermanArticle.DAS) null else GermanArticle.DAS
                        },
                        label = { Text("das") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ArticleDas.copy(alpha = 0.2f),
                            selectedLabelColor = ArticleDas
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Word List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredWords, key = { it.id }) { word ->
                    WordVocabularyCard(word = word)
                }
            }
        }
    }
}

@Composable
private fun WordVocabularyCard(word: WordItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ArticleTag(article = word.article)
                    Text(
                        text = word.german,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (word.plural.isNotBlank() && word.plural != "-") {
                    Text(
                        text = "die ${word.plural}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "🇹🇷 ${word.turkish}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )

            if (word.exampleSentence.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "\"${word.exampleSentence}\"",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                        )
                        if (word.exampleTranslation.isNotBlank()) {
                            Text(
                                text = word.exampleTranslation,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
