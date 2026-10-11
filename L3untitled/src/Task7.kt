open class Vehicle7(val brand: String)

class Car7(brand: String, val doors: Int) : Vehicle7(brand)

class Bike7(brand: String, val gears: Int) : Vehicle7(brand)

fun doorsOrZero(v: Vehicle7): Int { // Проверка есть ли у транспорта двери
    return if (v is Car7) { // двери есть значит машина
        v.doors
    } else {
        0
    }
}

fun main() {
    val car7: Vehicle7 = Car7("Volvo", 4)
    val bike7: Vehicle7 = Bike7("Trek", 3)

    println("Car doors: ${doorsOrZero(car7)}")
    println("Bike doors: ${doorsOrZero(bike7)}")
    val c = Bike7("X", 3) as? Car7 // проверка на машину, провал равен null
    if (c == null) { // если null проверка провалена транспорт не машина
        println("Safe cast failed: object is not a Car")
    } else {
        println("Car doors: ${c.doors}")
    }
}
