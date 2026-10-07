package com.example.data.model

enum class GermanArticle(val value: String, val displayName: String) {
    DER("der", "der"),
    DIE("die", "die"),
    DAS("das", "das"),
    DIE_PLURAL("die (Pl.)", "die"),
    DER_DAS("der/das", "der/das"),
    NONE("", "");

    companion object {
        fun fromString(str: String): GermanArticle {
            return when (str.trim().lowercase()) {
                "der" -> DER
                "die" -> DIE
                "das" -> DAS
                "die (pl.)", "die (pl)" -> DIE_PLURAL
                "der/das", "der / das" -> DER_DAS
                else -> NONE
            }
        }
    }
}

enum class WordCategory(val titleTr: String, val titleDe: String, val iconName: String) {
    ESSEN_TRINKEN("Yiyecek & İçecekler", "Essen & Trinken", "restaurant"),
    FAMILIE_PERSONEN("Aile & Tanışma", "Meine Familie", "groups"),
    LAENDER_SPRACHEN("Ülkeler & Diller", "Länder & Sprachen", "public"),
    ALLTAG_FORMULAR("Kişisel Bilgiler & Form", "Formular & Alltag", "description")
}

data class WordItem(
    val id: String,
    val german: String,                // e.g. "Apfel", "Banane", "lernen"
    val article: GermanArticle,        // DER, DIE, DAS, NONE
    val plural: String = "",           // e.g. "Äpfel", "-n", "-er"
    val turkish: String,               // e.g. "elma", "öğretmen"
    val category: WordCategory,
    val exampleSentence: String = "",  // e.g. "Das ist ein Apfel."
    val exampleTranslation: String = "",
    val isNoun: Boolean = article != GermanArticle.NONE
) {
    val fullGermanWithArticle: String
        get() = if (article != GermanArticle.NONE) "${article.displayName} $german" else german
}

enum class GameMode(val title: String, val subtitle: String, val tag: String) {
    ARTIKEL("Artikel Ustası", "der, die veya das? Doğru artikeli tahmin et!", "artikel"),
    TR_TO_DE("Türkçe ➔ Almanca", "Kelimeyi artikeliyle birlikte eksiksiz yaz!", "tr_to_de"),
    DE_TO_TR("Almanca ➔ Türkçe", "Almanca kelimenin Türkçe karşılığını seç!", "de_to_tr"),
    CHAMPIONSHIP("Karışık Şampiyona", "Tüm modlardan karma sorularla yarış!", "karisik"),
    MISTAKES_REVIEW("Hataları Tekrar Et", "Önceki oyunlarda takıldığın kelimeler!", "hatalar")
}

data class QuizQuestion(
    val word: WordItem,
    val mode: GameMode,
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctAnswer: String,
    val expectedArticle: GermanArticle = word.article,
    val hint: String = ""
)

data class AnswerEvaluation(
    val isCorrect: Boolean,
    val userAnswer: String,
    val correctAnswer: String,
    val word: WordItem,
    val articleError: Boolean = false,
    val spellingError: Boolean = false,
    val explanation: String = ""
)
