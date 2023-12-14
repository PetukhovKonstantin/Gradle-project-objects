class ChatNotFoundException(message: String) : RuntimeException(message)

data class DirectChat(
    val id: Int,
    val friendId: Int,
    val title: String
)