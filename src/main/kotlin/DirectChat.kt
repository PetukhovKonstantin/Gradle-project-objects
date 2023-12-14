class ChatNotFoundException(chatId: Int) : RuntimeException("Chat with id $chatId not found")

data class DirectChat(
    val id: Int,
    val friendId: Int,
    val title: String
)
