open class Parcel(val id: String, val weightKg: Double) {
    init {
        require(id.isNotBlank()) {
            "ID посылки не должен быть пустым"
        }
        require(weightKg.isFinite() && weightKg > 0) {
            "Вес должен быть положительным конечным числом"
        }
    }
    open fun shippingCost(): Double { // сколько стои посылка весс умногкный на 2
        return weightKg * 2.0
    }
}
class ExpressParcel(id: String, weightKg: Double): // экспресс доставка добавим 15 к цене
    Parcel(id, weightKg) {

    override fun shippingCost(): Double {
        return super.shippingCost() + 15.0
    }
}

class EconomyParcel(id: String, weightKg: Double): // эканом моставка умножим на 0.8
    Parcel(id, weightKg) {

    override fun shippingCost(): Double {
        return super.shippingCost() * 0.8
    }
}

fun totalShipping(parcels: List<Parcel>): Double { // общяя сумма со всех посылок
    return parcels.sumOf { it.shippingCost() }
}

fun main() {
    val parcels: List<Parcel> = listOf(
        Parcel("P001", 5.0),
        ExpressParcel("P002", 3.0),
        EconomyParcel("P003", 10.0),
        ExpressParcel("P004", 2.5)
    )

    val total = totalShipping(parcels)
    println("Total shipping cost: %.2f".format(total))
}
