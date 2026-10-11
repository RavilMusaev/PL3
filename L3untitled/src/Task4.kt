open class Employee(val name: String, val salary: Int) { // одаём класс сотркдника с иминем и зарплатой
    init {
        require(name.isNotBlank()) { // проверка на ошибочный ввода
            "Имя сотрудника не должно быть пустым"
        }
        require(salary >= 0) { // проверка на ошибку ввода
            "Зарплата не может быть отрицательной"
        }
    }

    open fun pay(): Int = salary // тип данных зарплаты

    open fun describe(): String = "$name: ${pay()}" // формат вывода ввиде страки
}

class Accountant(  // класс бухгалтера
    name: String,
    salary: Int,
    val bonusPercent: Int // добовляем значение бонуса
) : Employee(name, salary) {

    init {
        require(bonusPercent in 0..100) { // проверяем верный ввод информации
            "Процент бонуса должен быть от 0 до 100"
        }
    }

    override fun pay(): Int { // обновляем выплату
        return super.pay() + super.pay() * bonusPercent / 100
    }

    override fun describe(): String { // обновляем сотрудника, професия + имя
        return "Accountant ${super.describe()}"
    }
}

class Intern( // класс стажёр
    name: String,
    salary: Int,
    val mentor: String // новое значение наставник
) : Employee(name, salary) {

    init {
        require(mentor.isNotBlank()) { // проверка корекности ввода
            "Имя наставника не должно быть пустым"
        }
    }

    override fun pay(): Int { // обновляем выплату для стажёра
        return super.pay() / 2
    }
}

fun main() {
    val employees: List<Employee> = listOf(
        Employee("Alex", 30000),
        Accountant("Maria", 50000, 20),
        Intern("Ivan", 20000, "Maria")
    )

    for (employee in employees) {
        println(employee.describe())
    }

    val totalPay = employees.sumOf { it.pay() }

    println("Total payroll: $totalPay")
}
