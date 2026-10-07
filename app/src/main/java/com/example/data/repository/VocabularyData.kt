package com.example.data.repository

import com.example.data.model.GermanArticle
import com.example.data.model.WordCategory
import com.example.data.model.WordItem

object VocabularyData {

    val allWords: List<WordItem> = listOf(
        // ================= ESSEN & TRINKEN =================
        WordItem(
            id = "apfel",
            german = "Apfel",
            article = GermanArticle.DER,
            plural = "Äpfel",
            turkish = "elma",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist ein roter Apfel.",
            exampleTranslation = "Bu kırmızı bir elmadır."
        ),
        WordItem(
            id = "banane",
            german = "Banane",
            article = GermanArticle.DIE,
            plural = "Bananen",
            turkish = "muz",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Lili kauft Bananen.",
            exampleTranslation = "Lili muz satın alıyor."
        ),
        WordItem(
            id = "birne",
            german = "Birne",
            article = GermanArticle.DIE,
            plural = "Birnen",
            turkish = "armut",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist keine Birne.",
            exampleTranslation = "Bu armut değil."
        ),
        WordItem(
            id = "butter",
            german = "Butter",
            article = GermanArticle.DIE,
            plural = "-",
            turkish = "tereyağı",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wir brauchen Butter.",
            exampleTranslation = "Tereyağına ihtiyacımız var."
        ),
        WordItem(
            id = "brot",
            german = "Brot",
            article = GermanArticle.DAS,
            plural = "Brote",
            turkish = "ekmek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Im Einkaufswagen sind Brote.",
            exampleTranslation = "Alışveriş sepetinde ekmekler var."
        ),
        WordItem(
            id = "broetchen",
            german = "Brötchen",
            article = GermanArticle.DAS,
            plural = "Brötchen",
            turkish = "küçük ekmek / sandviç ekmeği",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist doch kein Brötchen.",
            exampleTranslation = "Bu sandviç ekmeği değil ki."
        ),
        WordItem(
            id = "ei",
            german = "Ei",
            article = GermanArticle.DAS,
            plural = "Eier",
            turkish = "yumurta",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Sie brauchen drei Eier.",
            exampleTranslation = "Onların üç yumurtaya ihtiyacı var."
        ),
        WordItem(
            id = "fleisch",
            german = "Fleisch",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "et",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben wir Fleisch?",
            exampleTranslation = "Et var mı?"
        ),
        WordItem(
            id = "fisch",
            german = "Fisch",
            article = GermanArticle.DER,
            plural = "Fische",
            turkish = "balık",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich kaufe zwei Fische.",
            exampleTranslation = "İki balık satın alıyorum."
        ),
        WordItem(
            id = "gemuese",
            german = "Gemüse",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "sebze",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist frisches Gemüse.",
            exampleTranslation = "Bu taze sebzedir."
        ),
        WordItem(
            id = "obst",
            german = "Obst",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "meyve",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ist das Obst frisch?",
            exampleTranslation = "Meyve taze mi?"
        ),
        WordItem(
            id = "kaese",
            german = "Käse",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "peynir",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben wir Käse im Kühlschrank?",
            exampleTranslation = "Buzdolabında peynir var mı?"
        ),
        WordItem(
            id = "mehl",
            german = "Mehl",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "un",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Lara und Sofia haben Mehl.",
            exampleTranslation = "Lara ve Sofia'da un var."
        ),
        WordItem(
            id = "milch",
            german = "Milch",
            article = GermanArticle.DIE,
            plural = "-",
            turkish = "süt",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Herr Meier hat Milch.",
            exampleTranslation = "Bay Meier'de süt var."
        ),
        WordItem(
            id = "zucker",
            german = "Zucker",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "şeker",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wir machen Pfannkuchen mit Zucker.",
            exampleTranslation = "Şekerle pankek yapıyoruz."
        ),
        WordItem(
            id = "salz",
            german = "Salz",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "tuz",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben Sie Salz?",
            exampleTranslation = "Tuzunuz var mı?"
        ),
        WordItem(
            id = "pfeffer",
            german = "Pfeffer",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "karabiber",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich brauche Salz und Pfeffer.",
            exampleTranslation = "Tuz ve karabibere ihtiyacım var."
        ),
        WordItem(
            id = "schokolade",
            german = "Schokolade",
            article = GermanArticle.DIE,
            plural = "-",
            turkish = "çikolata",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist leckere Schokolade.",
            exampleTranslation = "Bu lezzetli bir çikolata."
        ),
        WordItem(
            id = "kartoffel",
            german = "Kartoffel",
            article = GermanArticle.DIE,
            plural = "Kartoffeln",
            turkish = "patates",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich kaufe ein Kilo Kartoffeln.",
            exampleTranslation = "Bir kilo patates satın alıyorum."
        ),
        WordItem(
            id = "kuchen",
            german = "Kuchen",
            article = GermanArticle.DER,
            plural = "Kuchen",
            turkish = "pasta / kek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist ein schöner Kuchen.",
            exampleTranslation = "Bu güzel bir kek."
        ),
        WordItem(
            id = "kaffee",
            german = "Kaffee",
            article = GermanArticle.DER,
            plural = "Kaffees",
            turkish = "kahve",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Kaffee ist mein Lieblingsgetränk.",
            exampleTranslation = "Kahve benim en sevdiğim içecektir."
        ),
        WordItem(
            id = "tee",
            german = "Tee",
            article = GermanArticle.DER,
            plural = "Tees",
            turkish = "çay",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben wir Tee?",
            exampleTranslation = "Çayımız var mı?"
        ),
        WordItem(
            id = "saft",
            german = "Saft",
            article = GermanArticle.DER,
            plural = "Säfte",
            turkish = "meyve suyu",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist doch kein Saft.",
            exampleTranslation = "Bu meyve suyu değil."
        ),
        WordItem(
            id = "wasser",
            german = "Wasser",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "su",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Brauchen wir Wasser?",
            exampleTranslation = "Suya ihtiyacımız var mı?"
        ),
        WordItem(
            id = "mineralwasser",
            german = "Mineralwasser",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "maden suyu",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben wir Mineralwasser?",
            exampleTranslation = "Maden suyumuz var mı?"
        ),
        WordItem(
            id = "reis",
            german = "Reis",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "pirinç",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Brauchen wir Reis?",
            exampleTranslation = "Pirince ihtiyacımız var mı?"
        ),
        WordItem(
            id = "wein",
            german = "Wein",
            article = GermanArticle.DER,
            plural = "Weine",
            turkish = "şarap",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben wir Wein?",
            exampleTranslation = "Şarabımız var mı?"
        ),
        WordItem(
            id = "bier",
            german = "Bier",
            article = GermanArticle.DAS,
            plural = "Biere",
            turkish = "bira",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Haben wir Bier?",
            exampleTranslation = "Biramız var mı?"
        ),
        WordItem(
            id = "orange",
            german = "Orange",
            article = GermanArticle.DIE,
            plural = "Orangen",
            turkish = "portakal",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist keine Orange.",
            exampleTranslation = "Bu portakal değil."
        ),
        WordItem(
            id = "tomate",
            german = "Tomate",
            article = GermanArticle.DIE,
            plural = "Tomaten",
            turkish = "domates",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist keine Tomate.",
            exampleTranslation = "Bu domates değil."
        ),
        WordItem(
            id = "zwiebel",
            german = "Zwiebel",
            article = GermanArticle.DIE,
            plural = "Zwiebeln",
            turkish = "soğan",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich kaufe zwei Zwiebeln.",
            exampleTranslation = "İki soğan satın alıyorum."
        ),
        WordItem(
            id = "pfannkuchen",
            german = "Pfannkuchen",
            article = GermanArticle.DER,
            plural = "Pfannkuchen",
            turkish = "pankek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wir machen Pfannkuchen.",
            exampleTranslation = "Pankek yapıyoruz."
        ),
        WordItem(
            id = "wuerstchen",
            german = "Würstchen",
            article = GermanArticle.DAS,
            plural = "Würstchen",
            turkish = "küçük sosis",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das ist ein Würstchen.",
            exampleTranslation = "Bu küçük bir sosis."
        ),
        WordItem(
            id = "wurst",
            german = "Wurst",
            article = GermanArticle.DIE,
            plural = "Würste",
            turkish = "sucuk / sosis",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wie viel kostet Wurst?",
            exampleTranslation = "Sosisin fiyatı ne kadar?"
        ),
        WordItem(
            id = "hackfleisch",
            german = "Hackfleisch",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "kıyma",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "100 Gramm Hackfleisch kosten 1 Euro.",
            exampleTranslation = "100 gram kıyma 1 Euro tutuyor."
        ),
        WordItem(
            id = "oel",
            german = "Öl",
            article = GermanArticle.DAS,
            plural = "Öle",
            turkish = "sıvı yağ",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Was kostet ein Liter Öl?",
            exampleTranslation = "Bir litre yağ ne kadar tutuyor?"
        ),
        WordItem(
            id = "joghurt",
            german = "Joghurt",
            article = GermanArticle.DER_DAS,
            plural = "Joghurts",
            turkish = "yoğurt",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich kaufe drei Joghurts.",
            exampleTranslation = "Üç yoğurt satın alıyorum."
        ),
        WordItem(
            id = "flasche",
            german = "Flasche",
            article = GermanArticle.DIE,
            plural = "Flaschen",
            turkish = "şişe",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Eine Flasche Saft kostet 1,09 Euro.",
            exampleTranslation = "Bir şişe meyve suyu 1.09 Euro."
        ),
        WordItem(
            id = "dose",
            german = "Dose",
            article = GermanArticle.DIE,
            plural = "Dosen",
            turkish = "kutu / konserve kutusu",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Eine Dose Tomaten kostet 0,49 Euro.",
            exampleTranslation = "Bir kutu domates 0.49 Euro."
        ),
        WordItem(
            id = "sahne",
            german = "Sahne",
            article = GermanArticle.DIE,
            plural = "-",
            turkish = "krema",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Was kostet ein Becher Sahne?",
            exampleTranslation = "Bir kap krema ne kadar?"
        ),
        WordItem(
            id = "restaurant",
            german = "Restaurant",
            article = GermanArticle.DAS,
            plural = "Restaurants",
            turkish = "restoran",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wir essen im Restaurant.",
            exampleTranslation = "Restoranda yemek yiyoruz."
        ),
        WordItem(
            id = "mensa",
            german = "Mensa",
            article = GermanArticle.DIE,
            plural = "Mensen",
            turkish = "üniversite yemekhanesi",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wir essen in der Mensa.",
            exampleTranslation = "Yemekhanede yemek yiyoruz."
        ),
        WordItem(
            id = "steak",
            german = "Steak",
            article = GermanArticle.DAS,
            plural = "Steaks",
            turkish = "biftek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich esse Steak und Salat.",
            exampleTranslation = "Biftek ve salata yiyorum."
        ),
        WordItem(
            id = "salat",
            german = "Salat",
            article = GermanArticle.DER,
            plural = "Salate",
            turkish = "salata",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Der Salat schmeckt gut.",
            exampleTranslation = "Salata çok lezzetli."
        ),
        WordItem(
            id = "sosse",
            german = "Soße",
            article = GermanArticle.DIE,
            plural = "Soßen",
            turkish = "sos",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Spaghetti mit Tomatensoße.",
            exampleTranslation = "Domates soslu spagetti."
        ),
        WordItem(
            id = "haehnchen",
            german = "Hähnchen",
            article = GermanArticle.DAS,
            plural = "Hähnchen",
            turkish = "tavuk",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Mein Lieblingsessen ist Hähnchen.",
            exampleTranslation = "En sevdiğim yemek tavuk."
        ),
        WordItem(
            id = "pommes",
            german = "Pommes frites",
            article = GermanArticle.DIE_PLURAL,
            plural = "Pommes",
            turkish = "patates kızartması",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich esse Hähnchen und Pommes.",
            exampleTranslation = "Tavuk ve patates kızartması yiyorum."
        ),
        WordItem(
            id = "pizza",
            german = "Pizza",
            article = GermanArticle.DIE,
            plural = "Pizzen",
            turkish = "pizza",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich esse gern Pizza.",
            exampleTranslation = "Severek pizza yerim."
        ),
        WordItem(
            id = "durst",
            german = "Durst",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "susuzluk",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Leonie hat Durst.",
            exampleTranslation = "Leonie susadı."
        ),
        WordItem(
            id = "hunger",
            german = "Hunger",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "açlık",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ich habe Hunger.",
            exampleTranslation = "Ben açım / Karnım aç."
        ),
        WordItem(
            id = "glas",
            german = "Glas",
            article = GermanArticle.DAS,
            plural = "Gläser",
            turkish = "cam / bardak",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Ein Glas Wasser, bitte.",
            exampleTranslation = "Bir bardak su, lütfen."
        ),
        WordItem(
            id = "suppe",
            german = "Suppe",
            article = GermanArticle.DIE,
            plural = "Suppen",
            turkish = "çorba",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Mein Lieblingsessen ist Gemüsesuppe.",
            exampleTranslation = "En sevdiğim yemek sebze çorbasıdır."
        ),
        WordItem(
            id = "getraenk",
            german = "Getränk",
            article = GermanArticle.DAS,
            plural = "Getränke",
            turkish = "içecek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Kaffee ist mein Lieblingsgetränk.",
            exampleTranslation = "Kahve benim en sevdiğim içecektir."
        ),
        WordItem(
            id = "lebensmittel",
            german = "Lebensmittel",
            article = GermanArticle.DAS,
            plural = "Lebensmittel",
            turkish = "gıda maddesi / yiyecek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Milch und Brot sind Lebensmittel.",
            exampleTranslation = "Süt ve ekmek gıda maddeleridir."
        ),
        WordItem(
            id = "portion",
            german = "Portion",
            article = GermanArticle.DIE,
            plural = "Portionen",
            turkish = "porsiyon",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Für vier Portionen braucht man Kartoffeln.",
            exampleTranslation = "Dört porsiyon için patates gerekir."
        ),
        WordItem(
            id = "rezept",
            german = "Rezept",
            article = GermanArticle.DAS,
            plural = "Rezepte",
            turkish = "yemek tarifi / reçete",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Das Rezept ist ganz einfach.",
            exampleTranslation = "Yemek tarifi oldukça kolay."
        ),
        WordItem(
            id = "preis",
            german = "Preis",
            article = GermanArticle.DER,
            plural = "Preise",
            turkish = "fiyat",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wie hoch ist der Preis?",
            exampleTranslation = "Fiyat ne kadar?"
        ),

        // Fiiller & İfadeler (Essen & Trinken)
        WordItem(
            id = "essen_v",
            german = "essen",
            article = GermanArticle.NONE,
            turkish = "yemek yemek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Wir essen gern Fisch.",
            exampleTranslation = "Severek balık yeriz."
        ),
        WordItem(
            id = "trinken_v",
            german = "trinken",
            article = GermanArticle.NONE,
            turkish = "içmek",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Jens trinkt Wasser.",
            exampleTranslation = "Jens su içiyor."
        ),
        WordItem(
            id = "kochen_v",
            german = "kochen",
            article = GermanArticle.NONE,
            turkish = "pişirmek / yemek yapmak",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Hisako kocht Gemüsesuppe.",
            exampleTranslation = "Hisako sebze çorbası pişiriyor."
        ),
        WordItem(
            id = "schmecken_v",
            german = "schmecken",
            article = GermanArticle.NONE,
            turkish = "tadına bakmak / lezzetli olmak",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Hm, das schmeckt so gut!",
            exampleTranslation = "Hımm, bu çok lezzetli!"
        ),
        WordItem(
            id = "brauchen_v",
            german = "brauchen",
            article = GermanArticle.NONE,
            turkish = "ihtiyaç duymak / lazım olmak",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Sie brauchen Eier.",
            exampleTranslation = "Onların yumurtaya ihtiyacı var."
        ),
        WordItem(
            id = "kaufen_v",
            german = "kaufen",
            article = GermanArticle.NONE,
            turkish = "satın almak",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Lili kauft Bananen.",
            exampleTranslation = "Lili muz satın alıyor."
        ),
        WordItem(
            id = "kosten_v",
            german = "kosten",
            article = GermanArticle.NONE,
            turkish = "fiyatı olmak / tutmak",
            category = WordCategory.ESSEN_TRINKEN,
            exampleSentence = "Was kostet ein Kilo Käse?",
            exampleTranslation = "Bir kilo peynir ne kadar tutuyor?"
        ),

        // ================= FAMILIE & PERSONEN =================
        WordItem(
            id = "lehrer",
            german = "Lehrer",
            article = GermanArticle.DER,
            plural = "Lehrer",
            turkish = "öğretmen (erkek)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tim ist Laras Deutschlehrer.",
            exampleTranslation = "Tim, Lara'nın Almanca öğretmenidir."
        ),
        WordItem(
            id = "lehrerin",
            german = "Lehrerin",
            article = GermanArticle.DIE,
            plural = "Lehrerinnen",
            turkish = "öğretmen (kadın)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Sie ist eine gute Lehrerin.",
            exampleTranslation = "O iyi bir öğretmendir."
        ),
        WordItem(
            id = "pause",
            german = "Pause",
            article = GermanArticle.DIE,
            plural = "Pausen",
            turkish = "mola / ara",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tim und Lara haben Pause.",
            exampleTranslation = "Tim ve Lara mola veriyor."
        ),
        WordItem(
            id = "park",
            german = "Park",
            article = GermanArticle.DER,
            plural = "Parks",
            turkish = "park",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tim und Lara lernen im Park.",
            exampleTranslation = "Tim ve Lara parkta öğreniyor."
        ),
        WordItem(
            id = "familie",
            german = "Familie",
            article = GermanArticle.DIE,
            plural = "Familien",
            turkish = "aile",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das ist meine Familie.",
            exampleTranslation = "Bu benim ailem."
        ),
        WordItem(
            id = "vater",
            german = "Vater",
            article = GermanArticle.DER,
            plural = "Väter",
            turkish = "baba",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das ist Tims Vater.",
            exampleTranslation = "Bu Tim'in babası."
        ),
        WordItem(
            id = "mutter",
            german = "Mutter",
            article = GermanArticle.DIE,
            plural = "Mütter",
            turkish = "anne",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das ist Laras Mutter.",
            exampleTranslation = "Bu Lara'nın annesi."
        ),
        WordItem(
            id = "eltern",
            german = "Eltern",
            article = GermanArticle.DIE_PLURAL,
            plural = "Eltern",
            turkish = "ebeveyn",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das sind Tims Eltern.",
            exampleTranslation = "Bunlar Tim'in ebeveynleri."
        ),
        WordItem(
            id = "grosseltern",
            german = "Großeltern",
            article = GermanArticle.DIE_PLURAL,
            plural = "Großeltern",
            turkish = "büyük ebeveyn (dede & nine)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das sind Laras Großeltern.",
            exampleTranslation = "Bunlar Lara'nın büyük ebeveynleri."
        ),
        WordItem(
            id = "bruder",
            german = "Bruder",
            article = GermanArticle.DER,
            plural = "Brüder",
            turkish = "erkek kardeş",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das ist Tims Bruder.",
            exampleTranslation = "Bu Tim'in erkek kardeşi."
        ),
        WordItem(
            id = "schwester",
            german = "Schwester",
            article = GermanArticle.DIE,
            plural = "Schwestern",
            turkish = "kız kardeş",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Sofia ist meine Schwester.",
            exampleTranslation = "Sofia benim kız kardeşimdir."
        ),
        WordItem(
            id = "geschwister",
            german = "Geschwister",
            article = GermanArticle.DIE_PLURAL,
            plural = "Geschwister",
            turkish = "kardeşler",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Lara hat Geschwister.",
            exampleTranslation = "Lara'nın kardeşleri var."
        ),
        WordItem(
            id = "kind",
            german = "Kind",
            article = GermanArticle.DAS,
            plural = "Kinder",
            turkish = "çocuk",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das sind meine Kinder.",
            exampleTranslation = "Bunlar benim çocuklarım."
        ),
        WordItem(
            id = "sohn",
            german = "Sohn",
            article = GermanArticle.DER,
            plural = "Söhne",
            turkish = "erkek çocuk / oğul",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tobias ist Walters Sohn.",
            exampleTranslation = "Tobias, Walter'in oğludur."
        ),
        WordItem(
            id = "tochter",
            german = "Tochter",
            article = GermanArticle.DIE,
            plural = "Töchter",
            turkish = "kız çocuk",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Sofia ist Walters Tochter.",
            exampleTranslation = "Sofia, Walter'in kızıdır."
        ),
        WordItem(
            id = "enkel",
            german = "Enkel",
            article = GermanArticle.DER,
            plural = "Enkel",
            turkish = "torun (erkek)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tim ist Walters Enkel.",
            exampleTranslation = "Tim, Walter'in torunudur."
        ),
        WordItem(
            id = "enkelin",
            german = "Enkelin",
            article = GermanArticle.DIE,
            plural = "Enkelinnen",
            turkish = "torun (kız)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Lili ist Walters Enkelin.",
            exampleTranslation = "Lili, Walter'in torunudur."
        ),
        WordItem(
            id = "oma",
            german = "Oma",
            article = GermanArticle.DIE,
            plural = "Omas",
            turkish = "büyükanne / nine",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Luise ist Lilis Oma.",
            exampleTranslation = "Luise, Lili'nin ninesidir."
        ),
        WordItem(
            id = "grossmutter",
            german = "Großmutter",
            article = GermanArticle.DIE,
            plural = "Großmütter",
            turkish = "büyükanne",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Luise ist Lilis Großmutter.",
            exampleTranslation = "Luise, Lili'nin büyükannesidir."
        ),
        WordItem(
            id = "opa",
            german = "Opa",
            article = GermanArticle.DER,
            plural = "Opas",
            turkish = "büyükbaba / dede",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Walter ist Lilis Opa.",
            exampleTranslation = "Walter, Lili'nin dedesidir."
        ),
        WordItem(
            id = "grossvater",
            german = "Großvater",
            article = GermanArticle.DER,
            plural = "Großväter",
            turkish = "büyükbaba",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Walter ist Lilis Großvater.",
            exampleTranslation = "Walter, Lili'nin büyükbabasıdır."
        ),
        WordItem(
            id = "mann",
            german = "Mann",
            article = GermanArticle.DER,
            plural = "Männer",
            turkish = "adam / koca",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Walter ist mein Mann.",
            exampleTranslation = "Walter benim kocamdır."
        ),
        WordItem(
            id = "ehemann",
            german = "Ehemann",
            article = GermanArticle.DER,
            plural = "Ehemänner",
            turkish = "eş / koca",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Nein, das ist mein Ehemann.",
            exampleTranslation = "Hayır, bu benim eşim."
        ),
        WordItem(
            id = "ehefrau",
            german = "Ehefrau",
            article = GermanArticle.DIE,
            plural = "Ehefrauen",
            turkish = "eş / karı",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Maria ist deine Ehefrau.",
            exampleTranslation = "Maria senin eşindir."
        ),
        WordItem(
            id = "arzt",
            german = "Arzt",
            article = GermanArticle.DER,
            plural = "Ärzte",
            turkish = "doktor (erkek)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Ich heiße Franz und bin Arzt.",
            exampleTranslation = "Adım Franz ve doktorum."
        ),
        WordItem(
            id = "aerztin",
            german = "Ärztin",
            article = GermanArticle.DIE,
            plural = "Ärztinnen",
            turkish = "doktor (kadın)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Sie ist Ärztin von Beruf.",
            exampleTranslation = "Mesleği doktordur."
        ),
        WordItem(
            id = "partner",
            german = "Partner",
            article = GermanArticle.DER,
            plural = "Partner",
            turkish = "partner / ortak",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Das ist mein Partner.",
            exampleTranslation = "Bu benim partnerim."
        ),
        WordItem(
            id = "partnerin",
            german = "Partnerin",
            article = GermanArticle.DIE,
            plural = "Partnerinnen",
            turkish = "partner (kadın)",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Meine Partnerin kommt aus Dänemark.",
            exampleTranslation = "Partnerim Danimarka'dan geliyor."
        ),
        WordItem(
            id = "polizei",
            german = "Polizei",
            article = GermanArticle.DIE,
            plural = "-",
            turkish = "polis",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Ich bin bei der Polizei.",
            exampleTranslation = "Poliste çalışıyorum."
        ),
        WordItem(
            id = "familienstand",
            german = "Familienstand",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "medeni hal",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Familienstand: ledig, verheiratet...",
            exampleTranslation = "Medeni durum: bekar, evli..."
        ),
        WordItem(
            id = "alter",
            german = "Alter",
            article = GermanArticle.DAS,
            plural = "-",
            turkish = "yaş",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Alter: drei.",
            exampleTranslation = "Yaş: üç."
        ),

        // Sıfatlar & Fiiller (Familie)
        WordItem(
            id = "lernen_v",
            german = "lernen",
            article = GermanArticle.NONE,
            turkish = "öğrenmek",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tim lernt auch Deutsch.",
            exampleTranslation = "Tim de Almanca öğreniyor."
        ),
        WordItem(
            id = "haben_v",
            german = "haben",
            article = GermanArticle.NONE,
            turkish = "sahip olmak",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Wir haben Pause.",
            exampleTranslation = "Molamız var."
        ),
        WordItem(
            id = "leben_v",
            german = "leben",
            article = GermanArticle.NONE,
            turkish = "yaşamak",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Laras Vater lebt in Poznań.",
            exampleTranslation = "Lara'nın babası Poznan'da yaşıyor."
        ),
        WordItem(
            id = "wohnen_v",
            german = "wohnen",
            article = GermanArticle.NONE,
            turkish = "ikamet etmek / oturmak",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Tim wohnt jetzt in München.",
            exampleTranslation = "Tim şimdi Münih'te oturuyor."
        ),
        WordItem(
            id = "verheiratet_adj",
            german = "verheiratet",
            article = GermanArticle.NONE,
            turkish = "evli",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Sind Sie verheiratet?",
            exampleTranslation = "Evli misiniz?"
        ),
        WordItem(
            id = "ledig_adj",
            german = "ledig",
            article = GermanArticle.NONE,
            turkish = "bekar",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Ich bin ledig.",
            exampleTranslation = "Bekarım."
        ),
        WordItem(
            id = "geschieden_adj",
            german = "geschieden",
            article = GermanArticle.NONE,
            turkish = "boşanmış",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Sie sind geschieden.",
            exampleTranslation = "Onlar boşanmış."
        ),
        WordItem(
            id = "verwitwet_adj",
            german = "verwitwet",
            article = GermanArticle.NONE,
            turkish = "dul",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Ich bin verwitwet.",
            exampleTranslation = "Dulum."
        ),
        WordItem(
            id = "geboren_adj",
            german = "geboren",
            article = GermanArticle.NONE,
            turkish = "doğmuş olmak",
            category = WordCategory.FAMILIE_PERSONEN,
            exampleSentence = "Wo sind Sie geboren?",
            exampleTranslation = "Nerede doğdunuz?"
        ),

        // ================= LÄNDER & SPRACHEN =================
        WordItem(
            id = "tuerkei",
            german = "die Türkei",
            article = GermanArticle.DIE,
            turkish = "Türkiye",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Türkisch",
            exampleTranslation = "Dil: Türkçe"
        ),
        WordItem(
            id = "deutschland",
            german = "Deutschland",
            article = GermanArticle.NONE,
            turkish = "Almanya",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "In Deutschland spricht man Deutsch.",
            exampleTranslation = "Almanya'da Almanca konuşulur."
        ),
        WordItem(
            id = "oesterreich",
            german = "Österreich",
            article = GermanArticle.NONE,
            turkish = "Avusturya",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Deutsch",
            exampleTranslation = "Dil: Almanca"
        ),
        WordItem(
            id = "schweiz",
            german = "die Schweiz",
            article = GermanArticle.DIE,
            turkish = "İsviçre",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "In der Schweiz spricht man Deutsch.",
            exampleTranslation = "İsviçre'de Almanca konuşulur."
        ),
        WordItem(
            id = "spanien",
            german = "Spanien",
            article = GermanArticle.NONE,
            turkish = "İspanya",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Spanisch",
            exampleTranslation = "Dil: İspanyolca"
        ),
        WordItem(
            id = "frankreich",
            german = "Frankreich",
            article = GermanArticle.NONE,
            turkish = "Fransa",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Französisch",
            exampleTranslation = "Dil: Fransızca"
        ),
        WordItem(
            id = "italien",
            german = "Italien",
            article = GermanArticle.NONE,
            turkish = "İtalya",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Italienisch",
            exampleTranslation = "Dil: İtalyanca"
        ),
        WordItem(
            id = "russland",
            german = "Russland",
            article = GermanArticle.NONE,
            turkish = "Rusya",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Russisch",
            exampleTranslation = "Dil: Rusça"
        ),
        WordItem(
            id = "grossbritannien",
            german = "Großbritannien",
            article = GermanArticle.NONE,
            turkish = "Birleşik Krallık",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Englisch",
            exampleTranslation = "Dil: İngilizce"
        ),
        WordItem(
            id = "griechenland",
            german = "Griechenland",
            article = GermanArticle.NONE,
            turkish = "Yunanistan",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Griechisch",
            exampleTranslation = "Dil: Yunanca"
        ),
        WordItem(
            id = "polen",
            german = "Polen",
            article = GermanArticle.NONE,
            turkish = "Polonya",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Polnisch",
            exampleTranslation = "Dil: Lehçe"
        ),
        WordItem(
            id = "china",
            german = "China",
            article = GermanArticle.NONE,
            turkish = "Çin",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Sprache: Chinesisch",
            exampleTranslation = "Dil: Çince"
        ),
        WordItem(
            id = "deutsch_lang",
            german = "Deutsch",
            article = GermanArticle.NONE,
            turkish = "Almanca",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Wir lernen Deutsch.",
            exampleTranslation = "Almanca öğreniyoruz."
        ),
        WordItem(
            id = "tuerkisch_lang",
            german = "Türkisch",
            article = GermanArticle.NONE,
            turkish = "Türkçe",
            category = WordCategory.LAENDER_SPRACHEN,
            exampleSentence = "Ich spreche Türkisch.",
            exampleTranslation = "Türkçe konuşuyorum."
        ),

        // ================= ALLTAG & FORMULAR =================
        WordItem(
            id = "sport",
            german = "Sport",
            article = GermanArticle.DER,
            plural = "-",
            turkish = "spor",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Sport ist super.",
            exampleTranslation = "Spor süperdir."
        ),
        WordItem(
            id = "vorname",
            german = "Vorname",
            article = GermanArticle.DER,
            plural = "Vornamen",
            turkish = "ilk ad / ön ad",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Vorname: Heidi, Francesco.",
            exampleTranslation = "Ön ad: Heidi, Francesco."
        ),
        WordItem(
            id = "familienname",
            german = "Familienname",
            article = GermanArticle.DER,
            plural = "Familiennamen",
            turkish = "soyadı",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Familienname: Morbacher.",
            exampleTranslation = "Soyadı: Morbacher."
        ),
        WordItem(
            id = "strasse",
            german = "Straße",
            article = GermanArticle.DIE,
            plural = "Straßen",
            turkish = "cadde / sokak",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Straße: Keplerstraße.",
            exampleTranslation = "Cadde: Keplerstraße."
        ),
        WordItem(
            id = "stadt",
            german = "Stadt",
            article = GermanArticle.DIE,
            plural = "Städte",
            turkish = "şehir",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Stadt: Graz, Zürich, Mainz.",
            exampleTranslation = "Şehir: Graz, Zürih, Mainz."
        ),
        WordItem(
            id = "hauptstadt",
            german = "Hauptstadt",
            article = GermanArticle.DIE,
            plural = "Hauptstädte",
            turkish = "başkent",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Berlin ist die Hauptstadt von Deutschland.",
            exampleTranslation = "Berlin, Almanya'nın başkentidir."
        ),
        WordItem(
            id = "land",
            german = "Land",
            article = GermanArticle.DAS,
            plural = "Länder",
            turkish = "ülke",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Land: Österreich, Schweiz, Deutschland.",
            exampleTranslation = "Ülke: Avusturya, İsviçre, Almanya."
        ),
        WordItem(
            id = "telefon",
            german = "Telefon",
            article = GermanArticle.DAS,
            plural = "Telefone",
            turkish = "telefon",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Telefon: 040-42 83 80.",
            exampleTranslation = "Telefon: 040-42 83 80."
        ),
        WordItem(
            id = "fax",
            german = "Fax",
            article = GermanArticle.DAS,
            plural = "Faxe",
            turkish = "faks",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Fax: (089) 2 88 14 29.",
            exampleTranslation = "Faks: (089) 2 88 14 29."
        ),
        WordItem(
            id = "email",
            german = "E-Mail",
            article = GermanArticle.DIE,
            plural = "E-Mails",
            turkish = "e-posta",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "E-Mail: info@jojo-reisen.info",
            exampleTranslation = "E-posta: info@jojo-reisen.info"
        ),
        WordItem(
            id = "formular",
            german = "Formular",
            article = GermanArticle.DAS,
            plural = "Formulare",
            turkish = "form",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Ergänzen Sie das Formular.",
            exampleTranslation = "Formu doldurunuz."
        ),
        WordItem(
            id = "postleitzahl",
            german = "Postleitzahl",
            article = GermanArticle.DIE,
            plural = "Postleitzahlen",
            turkish = "posta kodu",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Die Postleitzahl ist 1700.",
            exampleTranslation = "Posta kodu 1700'dür."
        ),
        WordItem(
            id = "geburtsort",
            german = "Geburtsort",
            article = GermanArticle.DER,
            plural = "Geburtsorte",
            turkish = "doğum yeri / memleket",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Geburtsort: Biasca.",
            exampleTranslation = "Doğum yeri: Biasca."
        ),
        WordItem(
            id = "wohnort",
            german = "Wohnort",
            article = GermanArticle.DER,
            plural = "Wohnorte",
            turkish = "ikametgah / yaşanılan yer",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Wohnort: Hamburg.",
            exampleTranslation = "İkametgah: Hamburg."
        ),
        WordItem(
            id = "liste",
            german = "Liste",
            article = GermanArticle.DIE,
            plural = "Listen",
            turkish = "liste",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Machen Sie eine Liste.",
            exampleTranslation = "Bir liste yapınız."
        ),
        WordItem(
            id = "nummer",
            german = "Nummer",
            article = GermanArticle.DIE,
            plural = "Nummern",
            turkish = "numara",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Wie ist Ihre Telefonnummer?",
            exampleTranslation = "Telefon numaranız nedir?"
        ),
        WordItem(
            id = "zahl",
            german = "Zahl",
            article = GermanArticle.DIE,
            plural = "Zahlen",
            turkish = "sayı",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Zahl: 0, 1, 2...",
            exampleTranslation = "Sayılar: 0, 1, 2..."
        ),
        WordItem(
            id = "null",
            german = "Null",
            article = GermanArticle.DIE,
            plural = "Nullen",
            turkish = "sıfır",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Null ist eine Zahl.",
            exampleTranslation = "Sıfır bir sayıdır."
        ),
        WordItem(
            id = "jahr",
            german = "Jahr",
            article = GermanArticle.DAS,
            plural = "Jahre",
            turkish = "yıl / yaş",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Lara ist zwanzig Jahre alt.",
            exampleTranslation = "Lara yirmi yaşında."
        ),
        WordItem(
            id = "tag",
            german = "Tag",
            article = GermanArticle.DER,
            plural = "Tage",
            turkish = "gün",
            category = WordCategory.ALLTAG_FORMULAR,
            exampleSentence = "Das reicht für drei Tage.",
            exampleTranslation = "Bu üç güne yeter."
        )
    )

    // Filter only nouns with definite articles (der, die, das)
    val articleNouns: List<WordItem> by lazy {
        allWords.filter { it.article == GermanArticle.DER || it.article == GermanArticle.DIE || it.article == GermanArticle.DAS }
    }

    // Get random options for German -> Turkish multiple choice
    fun generateDistractors(correctWord: WordItem, count: Int = 3): List<String> {
        val candidates = allWords
            .filter { it.id != correctWord.id && it.category == correctWord.category }
            .map { it.turkish }
            .shuffled()
            .take(count)

        if (candidates.size < count) {
            val extra = allWords
                .filter { it.id != correctWord.id && !candidates.contains(it.turkish) }
                .map { it.turkish }
                .shuffled()
                .take(count - candidates.size)
            return (candidates + extra).distinct()
        }
        return candidates
    }

    // Get random options for Turkish -> German multiple choice
    fun generateGermanDistractors(correctWord: WordItem, count: Int = 3): List<String> {
        val candidates = allWords
            .filter { it.id != correctWord.id && it.category == correctWord.category }
            .map { it.fullGermanWithArticle }
            .shuffled()
            .take(count)

        if (candidates.size < count) {
            val extra = allWords
                .filter { it.id != correctWord.id && !candidates.contains(it.fullGermanWithArticle) }
                .map { it.fullGermanWithArticle }
                .shuffled()
                .take(count - candidates.size)
            return (candidates + extra).distinct()
        }
        return candidates
    }
}
