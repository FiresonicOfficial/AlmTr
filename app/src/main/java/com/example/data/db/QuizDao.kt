package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class ArticleStatsSummary(
    val totalDerCorrect: Int,
    val totalDerAttempted: Int,
    val totalDieCorrect: Int,
    val totalDieAttempted: Int,
    val totalDasCorrect: Int,
    val totalDasAttempted: Int
)

data class OverallStatsSummary(
    val totalGames: Int,
    val totalScore: Int,
    val highestScore: Int,
    val totalCorrect: Int,
    val totalQuestions: Int
)

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: QuizSessionRecord): Long

    @Query("SELECT * FROM quiz_sessions ORDER BY timestamp DESC LIMIT 30")
    fun getRecentSessions(): Flow<List<QuizSessionRecord>>

    @Query("SELECT COUNT(*) as totalGames, COALESCE(SUM(score), 0) as totalScore, COALESCE(MAX(score), 0) as highestScore, COALESCE(SUM(correctCount), 0) as totalCorrect, COALESCE(SUM(totalQuestions), 0) as totalQuestions FROM quiz_sessions")
    fun getOverallStats(): Flow<OverallStatsSummary>

    @Query("SELECT COALESCE(SUM(derCorrect), 0) as totalDerCorrect, COALESCE(SUM(derTotal), 0) as totalDerAttempted, COALESCE(SUM(dieCorrect), 0) as totalDieCorrect, COALESCE(SUM(dieTotal), 0) as totalDieAttempted, COALESCE(SUM(dasCorrect), 0) as totalDasCorrect, COALESCE(SUM(dasTotal), 0) as totalDasAttempted FROM quiz_sessions")
    fun getArticleStats(): Flow<ArticleStatsSummary>

    // Mistakes Management
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMistake(mistake: MistakeRecord)

    @Query("SELECT * FROM mistakes WHERE wordId = :wordId LIMIT 1")
    suspend fun getMistake(wordId: String): MistakeRecord?

    @Query("SELECT * FROM mistakes ORDER BY mistakeCount DESC, lastMistakeTimestamp DESC")
    fun getAllMistakes(): Flow<List<MistakeRecord>>

    @Query("DELETE FROM mistakes WHERE wordId = :wordId")
    suspend fun deleteMistake(wordId: String)

    @Query("DELETE FROM mistakes")
    suspend fun clearAllMistakes()
}
