class Sensor(val id: String, var reading: Double) { // оздаём класс датчик с val id который нельзя менять и var reading который можно менять
    init {
        require(id.isNotBlank()) { // даём правило на то что id не должно быть пустым
            "ID датчика не должен быть пустым"
        }
    }

    fun update(reading: Double) { // даём проветку числа на плаваюшую точку
        require(reading.isFinite()) {
            "Показание должно быть числом с плавающеё точкой"
        }

        this.reading = reading
    }

    override fun toString(): String { // Переводит все значение в стороку
        return "Sensor(id=$id, reading=$reading)"
    }
}

fun main() {
    val sensor1 = Sensor("S001", 25.5)
    val sensor2 = Sensor("S002", 18.3)
    //val sensor3 = Sensor("", 13.6)

    sensor1.update(27.8)

    println(sensor1)
    println(sensor2)
    //println(sensor3)
}
