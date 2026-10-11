open class Ticket(val code: String, val basePrice: Int) {
    init {
        require(code.isNotBlank()) { "Код билета не должен быть пустым" }
        require(basePrice >= 0) { "Цена не может быть отрицательной" }
    }

    open fun price(): Int = basePrice // задаём цену для билета
}

class AdultTicket(
    code: String,
    basePrice: Int
) : Ticket(code, basePrice)

class ChildTicket(
    code: String,
    basePrice: Int,
    val guardianCode: String // нужен опекун для ребёнка
) : Ticket(code, basePrice) {

    init {
        require(guardianCode.isNotBlank()) {
            "Код опекуна не должен быть пустым"
        }
    }

    override fun price(): Int = super.price() / 2 // детский билет стоит в 2 разаменьше
}

class VipTicket(
    code: String,
    basePrice: Int,
    val loungeAccess: Boolean // спец доступ
) : Ticket(code, basePrice) {

    override fun price(): Int {
        return super.price() + if (loungeAccess) 50 else 20
    }
}

fun checkout(tickets: List<Ticket>): Int { // вычисляем общю цену
    var total = 0

    for (ticket in tickets) {
        total += ticket.price()
    }

    return total
}

fun childGuardianCodes(tickets: List<Ticket>): List<String> {
    val guardians = mutableListOf<String>()

    for (ticket in tickets) {
        if (ticket is ChildTicket) {
            guardians.add(ticket.guardianCode) // Smart cast позволяет обратиться к guardianCode
        }
    }

    return guardians
}
fun main() {
    val tickets: List<Ticket> = listOf(
        AdultTicket("A-1", 100),
        ChildTicket("C-1", 40, "G-1"),
        VipTicket("V-1", 60, true),
        ChildTicket("C-2", 20, "G-9")
    )
    println("total=${checkout(tickets)}") // общяя цена
    println("guardians=${childGuardianCodes(tickets).joinToString(", ")}")
}
