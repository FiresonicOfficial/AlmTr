package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.db.ArticleStatsSummary
import com.example.data.db.MistakeRecord
import com.example.data.db.OverallStatsSummary
import com.example.data.db.QuizSessionRecord
import com.example.data.model.AnswerEvaluation
import com.example.data.model.GameMode
import com.example.data.model.GermanArticle
import com.example.data.model.QuizQuestion
import com.example.data.model.WordItem
import com.example.data.repository.VocabularyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    ARTIKEL_QUIZ,
    TRANSLATION_QUIZ,
    MULTI_CHOICE_QUIZ,
    RESULT,
    ANALYTICS,
    DICTIONARY
}

data class ActiveGameState(
    val mode: GameMode = GameMode.ARTIKEL,
    val questions: List<QuizQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val score: Int = 0,
    val streak: Int = 0,
    val maxStreak: Int = 0,
    val answers: List<AnswerEvaluation> = emptyList(),
    val startTimeMs: Long = System.currentTimeMillis(),
    val isAnswerSubmitted: Boolean = false,
    val lastEvaluation: AnswerEvaluation? = null,
    // Article tracking for session
    val derCorrect: Int = 0,
    val derTotal: Int = 0,
    val dieCorrect: Int = 0,
    val dieTotal: Int = 0,
    val dasCorrect: Int = 0,
    val dasTotal: Int = 0
) {
    val currentQuestion: QuizQuestion?
        get() = questions.getOrNull(currentIndex)

    val progress: Float
        get() = if (questions.isEmpty()) 0f else (currentIndex.toFloat() / questions.size)

    val isFinished: Boolean
        get() = questions.isNotEmpty() && currentIndex >= questions.size
}

class QuizViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val dao = db.quizDao()

    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _gameState = MutableStateFlow(ActiveGameState())
    val gameState: StateFlow<ActiveGameState> = _gameState.asStateFlow()

    // Persistent Room stats
    val overallStats: StateFlow<OverallStatsSummary> = dao.getOverallStats()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            OverallStatsSummary(0, 0, 0, 0, 0)
        )

    val articleStats: StateFlow<ArticleStatsSummary> = dao.getArticleStats()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ArticleStatsSummary(0, 0, 0, 0, 0, 0)
        )

    val recentSessions: StateFlow<List<QuizSessionRecord>> = dao.getRecentSessions()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allMistakes: StateFlow<List<MistakeRecord>> = dao.getAllMistakes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun startQuiz(mode: GameMode, questionCount: Int = 10) {
        val questions = buildQuestionsForMode(mode, questionCount)
        _gameState.value = ActiveGameState(
            mode = mode,
            questions = questions,
            currentIndex = 0,
            score = 0,
            streak = 0,
            maxStreak = 0,
            answers = emptyList(),
            startTimeMs = System.currentTimeMillis()
        )

        _currentScreen.value = when (mode) {
            GameMode.ARTIKEL -> AppScreen.ARTIKEL_QUIZ
            GameMode.TR_TO_DE -> AppScreen.TRANSLATION_QUIZ
            GameMode.DE_TO_TR -> AppScreen.MULTI_CHOICE_QUIZ
            GameMode.CHAMPIONSHIP -> {
                // If first question is translation or article, route accordingly
                val firstQ = questions.firstOrNull()
                when (firstQ?.mode) {
                    GameMode.TR_TO_DE -> AppScreen.TRANSLATION_QUIZ
                    GameMode.ARTIKEL -> AppScreen.ARTIKEL_QUIZ
                    else -> AppScreen.MULTI_CHOICE_QUIZ
                }
            }
            GameMode.MISTAKES_REVIEW -> {
                val firstQ = questions.firstOrNull()
                when (firstQ?.mode) {
                    GameMode.TR_TO_DE -> AppScreen.TRANSLATION_QUIZ
                    GameMode.ARTIKEL -> AppScreen.ARTIKEL_QUIZ
                    else -> AppScreen.MULTI_CHOICE_QUIZ
                }
            }
        }
    }

    private fun buildQuestionsForMode(mode: GameMode, count: Int): List<QuizQuestion> {
        return when (mode) {
            GameMode.ARTIKEL -> {
                val words = VocabularyData.articleNouns.shuffled().take(count)
                words.map { word ->
                    QuizQuestion(
                        word = word,
                        mode = GameMode.ARTIKEL,
                        prompt = word.german,
                        options = listOf("der", "die", "das"),
                        correctAnswer = word.article.value,
                        expectedArticle = word.article,
                        hint = word.turkish
                    )
                }
            }
            GameMode.TR_TO_DE -> {
                val words = VocabularyData.allWords.shuffled().take(count)
                words.map { word ->
                    val expected = word.fullGermanWithArticle
                    QuizQuestion(
                        word = word,
                        mode = GameMode.TR_TO_DE,
                        prompt = word.turkish,
                        correctAnswer = expected,
                        expectedArticle = word.article,
                        hint = if (word.isNoun) "Artikel ile birlikte yaz (örn: der/die/das ${word.german})" else "Almanca fiil/ifade"
                    )
                }
            }
            GameMode.DE_TO_TR -> {
                val words = VocabularyData.allWords.shuffled().take(count)
                words.map { word ->
                    val options = (VocabularyData.generateDistractors(word, 3) + word.turkish).shuffled()
                    QuizQuestion(
                        word = word,
                        mode = GameMode.DE_TO_TR,
                        prompt = word.fullGermanWithArticle,
                        options = options,
                        correctAnswer = word.turkish,
                        expectedArticle = word.article,
                        hint = word.exampleSentence
                    )
                }
            }
            GameMode.CHAMPIONSHIP -> {
                // Mix of Artikel, TR->DE, DE->TR
                val articleQuestions = VocabularyData.articleNouns.shuffled().take(count / 3).map { word ->
                    QuizQuestion(
                        word = word,
                        mode = GameMode.ARTIKEL,
                        prompt = word.german,
                        options = listOf("der", "die", "das"),
                        correctAnswer = word.article.value,
                        expectedArticle = word.article,
                        hint = word.turkish
                    )
                }
                val trDeQuestions = VocabularyData.allWords.shuffled().take(count / 3).map { word ->
                    QuizQuestion(
                        word = word,
                        mode = GameMode.TR_TO_DE,
                        prompt = word.turkish,
                        correctAnswer = word.fullGermanWithArticle,
                        expectedArticle = word.article,
                        hint = if (word.isNoun) "Artikel ile birlikte yaz (der/die/das)" else "Almanca"
                    )
                }
                val deTrQuestions = VocabularyData.allWords.shuffled().take(count - articleQuestions.size - trDeQuestions.size).map { word ->
                    QuizQuestion(
                        word = word,
                        mode = GameMode.DE_TO_TR,
                        prompt = word.fullGermanWithArticle,
                        options = (VocabularyData.generateDistractors(word, 3) + word.turkish).shuffled(),
                        correctAnswer = word.turkish,
                        expectedArticle = word.article,
                        hint = word.exampleSentence
                    )
                }
                (articleQuestions + trDeQuestions + deTrQuestions).shuffled()
            }
            GameMode.MISTAKES_REVIEW -> {
                val mistakes = allMistakes.value
                val mistakeWordIds = mistakes.map { it.wordId }.toSet()
                val targetWords = VocabularyData.allWords.filter { it.id in mistakeWordIds }
                val pool = if (targetWords.isNotEmpty()) targetWords.shuffled().take(count) else VocabularyData.allWords.shuffled().take(count)

                pool.map { word ->
                    if (word.isNoun && (0..1).random() == 0) {
                        QuizQuestion(
                            word = word,
                            mode = GameMode.TR_TO_DE,
                            prompt = word.turkish,
                            correctAnswer = word.fullGermanWithArticle,
                            expectedArticle = word.article,
                            hint = "Artikel ile yazınız"
                        )
                    } else if (word.isNoun) {
                        QuizQuestion(
                            word = word,
                            mode = GameMode.ARTIKEL,
                            prompt = word.german,
                            options = listOf("der", "die", "das"),
                            correctAnswer = word.article.value,
                            expectedArticle = word.article,
                            hint = word.turkish
                        )
                    } else {
                        QuizQuestion(
                            word = word,
                            mode = GameMode.DE_TO_TR,
                            prompt = word.fullGermanWithArticle,
                            options = (VocabularyData.generateDistractors(word, 3) + word.turkish).shuffled(),
                            correctAnswer = word.turkish,
                            expectedArticle = word.article,
                            hint = word.exampleSentence
                        )
                    }
                }
            }
        }
    }

    // Evaluate Artikel guess
    fun submitArticleAnswer(articleGuess: String) {
        val q = _gameState.value.currentQuestion ?: return
        val normalizedGuess = articleGuess.trim().lowercase()
        val isCorrect = normalizedGuess == q.correctAnswer.lowercase() ||
                (q.word.article == GermanArticle.DER_DAS && (normalizedGuess == "der" || normalizedGuess == "das"))

        val explanation = if (isCorrect) {
            "Harika! Doğru artikel: ${q.correctAnswer} ${q.word.german}"
        } else {
            "Hatalı! Doğru artikel: '${q.correctAnswer}'. ${q.word.fullGermanWithArticle} (${q.word.turkish})"
        }

        val eval = AnswerEvaluation(
            isCorrect = isCorrect,
            userAnswer = articleGuess,
            correctAnswer = q.correctAnswer,
            word = q.word,
            articleError = !isCorrect,
            explanation = explanation
        )

        recordEvaluation(eval)
    }

    // Evaluate TR -> DE text input (with strict article check)
    fun submitTranslationAnswer(userInput: String) {
        val q = _gameState.value.currentQuestion ?: return
        val cleanedInput = userInput.trim()
        val expected = q.correctAnswer.trim()

        val isExactMatch = cleanedInput.equals(expected, ignoreCase = true)

        var articleError = false
        var spellingError = false
        var explanation = ""
        var isCorrect = false

        if (isExactMatch) {
            isCorrect = true
            explanation = "Tebrikler! Eksiksiz ve doğru: $expected"
        } else if (q.word.isNoun) {
            // Check if input has correct article
            val inputParts = cleanedInput.split("\\s+".toRegex())
            val inputArticle = inputParts.firstOrNull()?.lowercase() ?: ""
            val inputNoun = if (inputParts.size > 1) inputParts.drop(1).joinToString(" ") else ""

            val expectedParts = expected.split("\\s+".toRegex())
            val expectedArticle = expectedParts.firstOrNull()?.lowercase() ?: ""
            val expectedNoun = if (expectedParts.size > 1) expectedParts.drop(1).joinToString(" ") else ""

            val articleMatches = when (q.word.article) {
                GermanArticle.DER_DAS -> inputArticle == "der" || inputArticle == "das"
                GermanArticle.DIE_PLURAL -> inputArticle == "die"
                else -> inputArticle == expectedArticle
            }

            if (!articleMatches) {
                articleError = true
                explanation = if (inputParts.size <= 1) {
                    "Artikel eksik! İsimleri artikeliyle yazmalısın. Doğrusu: '$expected'"
                } else {
                    "Yanlış Artikel! '$inputArticle' yerine '${expectedParts.firstOrNull()}' olmalıydı. Doğrusu: '$expected'"
                }
            } else {
                // Article was right, but noun spelling was wrong
                spellingError = true
                explanation = "Yazım hatası! Yazılan: '$inputNoun', Doğrusu: '$expectedNoun'"
            }
        } else {
            // Non-noun
            spellingError = true
            explanation = "Hatalı! Doğrusu: '$expected'"
        }

        val eval = AnswerEvaluation(
            isCorrect = isCorrect,
            userAnswer = cleanedInput,
            correctAnswer = expected,
            word = q.word,
            articleError = articleError,
            spellingError = spellingError,
            explanation = explanation
        )

        recordEvaluation(eval)
    }

    // Evaluate Multiple Choice (DE -> TR)
    fun submitMultipleChoiceAnswer(selectedOption: String) {
        val q = _gameState.value.currentQuestion ?: return
        val isCorrect = selectedOption.trim().equals(q.correctAnswer.trim(), ignoreCase = true)
        val explanation = if (isCorrect) {
            "Doğru! ${q.word.fullGermanWithArticle} = ${q.word.turkish}"
        } else {
            "Yanlış! ${q.word.fullGermanWithArticle} = ${q.correctAnswer}"
        }

        val eval = AnswerEvaluation(
            isCorrect = isCorrect,
            userAnswer = selectedOption,
            correctAnswer = q.correctAnswer,
            word = q.word,
            explanation = explanation
        )

        recordEvaluation(eval)
    }

    private fun recordEvaluation(evaluation: AnswerEvaluation) {
        val current = _gameState.value
        val q = current.currentQuestion ?: return

        val newStreak = if (evaluation.isCorrect) current.streak + 1 else 0
        val newMaxStreak = maxOf(current.maxStreak, newStreak)
        val pointsEarned = if (evaluation.isCorrect) 100 + (current.streak * 25) else 0
        val newScore = current.score + pointsEarned

        var derC = current.derCorrect
        var derT = current.derTotal
        var dieC = current.dieCorrect
        var dieT = current.dieTotal
        var dasC = current.dasCorrect
        var dasT = current.dasTotal

        if (q.expectedArticle == GermanArticle.DER) {
            derT++
            if (evaluation.isCorrect) derC++
        } else if (q.expectedArticle == GermanArticle.DIE) {
            dieT++
            if (evaluation.isCorrect) dieC++
        } else if (q.expectedArticle == GermanArticle.DAS) {
            dasT++
            if (evaluation.isCorrect) dasC++
        }

        _gameState.value = current.copy(
            score = newScore,
            streak = newStreak,
            maxStreak = newMaxStreak,
            answers = current.answers + evaluation,
            isAnswerSubmitted = true,
            lastEvaluation = evaluation,
            derCorrect = derC,
            derTotal = derT,
            dieCorrect = dieC,
            dieTotal = dieT,
            dasCorrect = dasC,
            dasTotal = dasT
        )

        // If mistake made, persist to DB
        if (!evaluation.isCorrect) {
            viewModelScope.launch {
                val existing = dao.getMistake(q.word.id)
                val newCount = (existing?.mistakeCount ?: 0) + 1
                dao.insertOrUpdateMistake(
                    MistakeRecord(
                        wordId = q.word.id,
                        german = q.word.german,
                        article = q.word.article.value,
                        turkish = q.word.turkish,
                        category = q.word.category.titleTr,
                        mistakeCount = newCount,
                        lastMistakeTimestamp = System.currentTimeMillis(),
                        exampleSentence = q.word.exampleSentence
                    )
                )
            }
        } else if (current.mode == GameMode.MISTAKES_REVIEW) {
            // Decrement or clear mistake if answered correctly
            viewModelScope.launch {
                dao.deleteMistake(q.word.id)
            }
        }
    }

    fun proceedToNextQuestion() {
        val current = _gameState.value
        val nextIndex = current.currentIndex + 1

        if (nextIndex >= current.questions.size) {
            // Finished!
            finishQuizSession()
        } else {
            val nextQuestion = current.questions[nextIndex]
            _gameState.value = current.copy(
                currentIndex = nextIndex,
                isAnswerSubmitted = false,
                lastEvaluation = null
            )
            // Adapt screen if mixed mode
            if (current.mode == GameMode.CHAMPIONSHIP || current.mode == GameMode.MISTAKES_REVIEW) {
                _currentScreen.value = when (nextQuestion.mode) {
                    GameMode.ARTIKEL -> AppScreen.ARTIKEL_QUIZ
                    GameMode.TR_TO_DE -> AppScreen.TRANSLATION_QUIZ
                    else -> AppScreen.MULTI_CHOICE_QUIZ
                }
            }
        }
    }

    private fun finishQuizSession() {
        val current = _gameState.value
        val total = current.questions.size
        val correct = current.answers.count { it.isCorrect }
        val accuracy = if (total > 0) (correct.toFloat() / total) * 100f else 0f
        val timeSeconds = ((System.currentTimeMillis() - current.startTimeMs) / 1000).toInt()

        viewModelScope.launch {
            dao.insertSession(
                QuizSessionRecord(
                    mode = current.mode.title,
                    score = current.score,
                    correctCount = correct,
                    totalQuestions = total,
                    accuracyRate = accuracy,
                    derCorrect = current.derCorrect,
                    derTotal = current.derTotal,
                    dieCorrect = current.dieCorrect,
                    dieTotal = current.dieTotal,
                    dasCorrect = current.dasCorrect,
                    dasTotal = current.dasTotal,
                    timeTakenSeconds = timeSeconds
                )
            )
        }

        _currentScreen.value = AppScreen.RESULT
    }

    fun deleteMistakeWord(wordId: String) {
        viewModelScope.launch {
            dao.deleteMistake(wordId)
        }
    }

    fun clearAllMistakes() {
        viewModelScope.launch {
            dao.clearAllMistakes()
        }
    }
}
