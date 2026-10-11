open class Notification(private val payload: String) {

    protected fun body(): String = payload //Хронит текушее обновление

    open fun render(): String = body()
}

class EmailNotification(
    payload: String,
    val to: String // значение для кого
) : Notification(payload) { // как должно выйти увидомление
    override fun render(): String {
        return "Email to $to: ${body()}"
    }
}

class SmsNotification(
    payload: String,
    val phone: String // значение телефона
) : Notification(payload) {

    override fun render(): String {
        return "SMS to $phone: ${body()}"
    }
}

fun sendAll(items: List<Notification>) {
    for (item in items) {
        println(item.render())
    }
}

fun main() {
    val notifications: List<Notification> = listOf(
        EmailNotification("Привет! Как ты?", "user@gmail.com"),
        SmsNotification("Ваш код 1234", "+996555123456"),
        EmailNotification("Ваш заказ был отправлен.", "client@gmail.com")
    )

    sendAll(notifications)
}
