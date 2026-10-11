open class Vehicle(val brand: String) { // открытий класс транспорта со знпчением марок
    init {
        require(brand.isNotBlank()) { // проверяем чтобы марка ек бала пустой
            "Марка транспорта не должна быть пустой"
        }
    }

    open fun info(): String { // возврощяем значение марки
        return "Brand: $brand"
    }

    open fun maxSpeedKmh(): Int { // возврощяем зночение скорости
        return 0
    }
}

class Car(brand: String, val doors: Int) : Vehicle(brand) { // создаём значение машин
    init {
        require(doors >= 2) { //проверяем коррекность значение дверей
            "У автомобиля должно быть минимум 2 двери"
        }
    }
    override fun info(): String { // обновляем знацения в классе машин
        return "Car: brand=$brand, doors=$doors"
    }

    override fun maxSpeedKmh(): Int { // обновляем значание скорости
        return 200
    }
}

class Bike(brand: String, val gears: Int) : Vehicle(brand) { // создаём класс велосипеда
    init {
        require(gears >= 1) { // проверяем чтобы у велосипеда была как минимум 1 передача
            "У велосипеда должна быть минимум 1 передача"
        }
    }

    override fun info(): String {// обновляем знацения в классе велосипеда
        return "Bike: brand=$brand, gears=$gears"
    }

    override fun maxSpeedKmh(): Int { // обновляем значание скорости
        return 60
    }
}

fun main() {
    val car = Car("Toyota", 4)
    val bike = Bike("Trek", 6)

    println(car.info())
    println("Max speed: ${car.maxSpeedKmh()} km/h")

    println()

    println(bike.info())
    println("Max speed: ${bike.maxSpeedKmh()} km/h")
}
