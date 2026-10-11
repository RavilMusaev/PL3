open class Assessment(val title: String, val maxPoints: Int) {
    init {
        require(title.isNotBlank()) { "Название не должно быть пустым" }
        require(maxPoints > 0) { "Максимум баллов должен быть больше 0" }
    }
    open fun scoreTowardGrade(raw: Int): Double {
        return raw.coerceIn(0, maxPoints).toDouble() / maxPoints //держит баллы в заданных рамках
    }
}

class Quiz(
    title: String,
    maxPoints: Int,
    val penaltyIfLate: Double // значение штрафа
) : Assessment(title, maxPoints) {

    init {
        require(penaltyIfLate in 0.0..0.5) {
            "Штраф должен быть от 0.0 до 0.5"
        }
    }

    override fun scoreTowardGrade(raw: Int): Double {
        return super.scoreTowardGrade(raw) * (1.0 - penaltyIfLate)// расчитываем результат родительского класса затем уменьшаем его на штраф за опоздание
    }
}

class Project(
    title: String,
    maxPoints: Int,
    val bonusCap: Double // значение бонуса
) : Assessment(title, maxPoints) {

    init {
        require(bonusCap >= 0.0 && bonusCap.isFinite()) {
            "Лимит бонуса должен быть неотрицательным числом"
        }
    }

    override fun scoreTowardGrade(raw: Int): Double {
        val baseScore = super.scoreTowardGrade(raw)
        // базовая доля + бонусные баллы / максимум, общий результат ограничен значением 1.0 + bonusCap.
        val cappedRaw = minOf(
            raw.toDouble().coerceAtLeast(0.0), maxPoints * (1.0 + bonusCap)
        )
        val bonusRatio = (cappedRaw - maxPoints).coerceAtLeast(0.0) / maxPoints
        return (baseScore + bonusRatio).coerceAtMost(1.0 + bonusCap)
    }
}

fun main() {
    val assessments: List<Assessment> = listOf(
        Quiz("Quiz 1", 10, 0.1),
        Quiz("Quiz 2", 20, 0.2),
        Project("Project 1", 10, 0.2),
        Project("Project 2", 20, 0.1)
    )

    val raw = 12

    for (assessment in assessments) {
        val score = assessment.scoreTowardGrade(raw)
        println("${assessment.title}: ${"%.2f".format(score)}")
    }
}
