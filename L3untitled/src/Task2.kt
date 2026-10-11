class Counter(start: Int = 0) { // Создаем старт со зночением 0
    private var count: Int = start // оброщяться к значению count которыз содержит Int

    fun value(): Int { // Возврошяем текущее значение count
        return count
    }
    fun inc(step: Int = 1) { // увеличеваеи чётчик на количество шагов
        require(step > 0) { // проверка на и ошибочный шаг
            "Шаг должен быть больше нуля"
        }
        count += step
    }

    fun reset() { // Откатываем зночение на 0
        count = 0
    }
}

fun main() {
    val counter1 = Counter()

    println("Начальное значение: ${counter1.value()}")

    counter1.inc()
    println("После inc(): ${counter1.value()}")

    counter1.inc(2)
    println("После inc(2): ${counter1.value()}")

    val counter2 = Counter(10)
    println("Второй счётчик: ${counter2.value()}")

    counter2.inc(5)
    println("После inc(5): ${counter2.value()}")

    counter2.reset()
    println("После reset(): ${counter2.value()}")
}
