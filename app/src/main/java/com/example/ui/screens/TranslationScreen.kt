package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AnswerFeedbackCard
import com.example.ui.components.GermanSpecialCharsRow
import com.example.ui.components.QuizTopBar
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

@Composable
fun TranslationScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.gameState.collectAsState()
    val question = state.currentQuestion
    var textInput by remember(question?.prompt) { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    if (question == null) return

    Scaffold(
        topBar = {
            QuizTopBar(
                title = "Türkçe ➔ Almanca Yaz",
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

                // Turkish Prompt Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("translation_prompt_card"),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Türkçe Kelime",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = question.prompt,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Instruction info badge
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = question.hint,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (!state.isAnswerSubmitted) {
                    // Input Text Field
                    OutlinedTextField(
                        value = textInput,
                        onValueChange = { textInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                            .testTag("translation_input_field"),
                        placeholder = {
                            Text(
                                text = if (question.word.isNoun) "örn: der / die / das ..." else "Almanca yazınız..."
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (textInput.isNotBlank()) {
                                    viewModel.submitTranslationAnswer(textInput)
                                }
                            }
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // German Special Chars & Quick Article Chips
                    GermanSpecialCharsRow(
                        onCharClick = { char -> textInput += char },
                        onArticleInsert = { articlePrefix ->
                            // Replace or prepend article
                            val trimmed = textInput.trimStart()
                            val cleanRemaining = if (trimmed.startsWith("der ", ignoreCase = true) ||
                                trimmed.startsWith("die ", ignoreCase = true) ||
                                trimmed.startsWith("das ", ignoreCase = true)
                            ) {
                                trimmed.substring(4)
                            } else {
                                trimmed
                            }
                            textInput = articlePrefix + cleanRemaining
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Submit Button
                    Button(
                        onClick = {
                            if (textInput.isNotBlank()) {
                                viewModel.submitTranslationAnswer(textInput)
                            }
                        },
                        enabled = textInput.isNotBlank(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("submit_translation_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cevabı Onayla",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            // Feedback Card if submitted
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
