package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_sessions")
data class QuizSessionRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val mode: String,
    val score: Int,
    val correctCount: Int,
    val totalQuestions: Int,
    val accuracyRate: Float,
    val derCorrect: Int = 0,
    val derTotal: Int = 0,
    val dieCorrect: Int = 0,
    val dieTotal: Int = 0,
    val dasCorrect: Int = 0,
    val dasTotal: Int = 0,
    val timeTakenSeconds: Int = 0
)

@Entity(tableName = "mistakes")
data class MistakeRecord(
    @PrimaryKey
    val wordId: String,
    val german: String,
    val article: String,
    val turkish: String,
    val category: String,
    val mistakeCount: Int = 1,
    val lastMistakeTimestamp: Long = System.currentTimeMillis(),
    val exampleSentence: String = ""
)
