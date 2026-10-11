open class Vehicle6(val brand: String) {
    init {
        require(brand.isNotBlank()) {
            "Brand must not be blank"
        }
    }

    open fun info(): String {
        return "Vehicle $brand"
    }
}
class Car6(brand: String, val doors: Int) : Vehicle6(brand) {

    init {
        require(doors >= 2) {
            "Car must have at least 2 doors"
        }
    }

    override fun info(): String {
        return "Car $brand, doors=$doors"
    }
}
class Bike6(brand: String, val gears: Int) : Vehicle6(brand) {

    init {
        require(gears >= 1) {
            "Bike must have at least 1 gear"
        }
    }

    override fun info(): String {
        return "Bike $brand, gears=$gears"
    }
}
fun printFleet(fleet: List<Vehicle6>) {// создаём лист транспартов
    for (vehicle6 in fleet) {
        println(vehicle6.info())
    }
}
fun main() {
    val v: Vehicle6 = Car6("Volvo", 4)

    println(v.info())

    val fleet: List<Vehicle6> = listOf(
        Car6("Volvo", 4),
        Bike6("Trek", 18),
        Car6("Toyota", 4)
    )

    printFleet(fleet)
}
