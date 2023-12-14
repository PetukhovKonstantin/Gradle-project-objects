import java.time.LocalDateTime
import java.time.ZoneOffset

class MessageNotFoundException(msgId: Int) : RuntimeException("Message with id $msgId not found")

data class Message(
    val id: Int,
    val chatId: Int,
    val text: String,
    val date: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
    val isRead: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Message) return false

        if (id != other.id) return false
        if (chatId != other.chatId) return false
        if (text != other.text) return false
        if (date != other.date) return false
        if (isRead != other.isRead) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + chatId.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + isRead.hashCode()
        return result
    }
}
