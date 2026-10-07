package com.example

import com.example.data.model.GermanArticle
import com.example.data.repository.VocabularyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun vocabularyData_hasExtensiveWords() {
        assertTrue("Kelime listesi boş olmamalı", VocabularyData.allWords.isNotEmpty())
        assertTrue("En az 50 kelime bulunmalı", VocabularyData.allWords.size >= 50)
    }

    @Test
    fun vocabularyData_articlesAreValid() {
        val nouns = VocabularyData.articleNouns
        assertTrue("İsimler listesi boş olmamalı", nouns.isNotEmpty())
        nouns.forEach { word ->
            assertTrue(
                "Artikel geçerli der, die veya das olmalı: ${word.german}",
                word.article == GermanArticle.DER ||
                        word.article == GermanArticle.DIE ||
                        word.article == GermanArticle.DAS ||
                        word.article == GermanArticle.DER_DAS ||
                        word.article == GermanArticle.DIE_PLURAL
            )
        }
    }

    @Test
    fun vocabularyData_distractorGenerationWorks() {
        val word = VocabularyData.allWords.first()
        val distractors = VocabularyData.generateDistractors(word, 3)
        assertEquals(3, distractors.size)
        assertTrue(!distractors.contains(word.turkish))
    }
}
