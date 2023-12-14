object ChatService {
    private var chats = mutableListOf<DirectChat>()
    private var messages = mutableListOf<Message>()
    private var lastId = 0
    private var lastIdMessage = 0

    fun add(chat: DirectChat, msg: Message): DirectChat {
        chats.add(chat.copy(id = ++lastId))
        createMessage(lastId, msg)
        return chats.last()
    }

    fun delete(chatId: Int): Boolean {
        chats.remove(getById(chatId))
        messages.removeAll { it.chatId == chatId }
        return true
    }

    fun get(): List<DirectChat> = chats

    fun getById(chatId: Int): DirectChat = chats.find { it.id == chatId } ?: throw ChatNotFoundException(chatId)

    fun createMessage(chatId: Int, msg: Message): Message {
        val chat = getById(chatId)
        messages.add(msg.copy(id = ++lastIdMessage, chatId = chat.id))
        return messages.last()
    }

    fun editMessage(msg: Message): Boolean  {
        val commentInList = getMessageById(msg.id)
        val index = messages.indexOf(commentInList)
        messages.removeAt(index)
        messages.add(index, msg.copy())
        return true
    }

    fun deleteMessage(msgId: Int): Boolean {
        val msg = getMessageById(msgId)
        messages.remove(msg)
        if (messages.none { it.chatId == msg.chatId }) delete(msg.chatId)
        return true
    }

    fun getMessageById(msgId: Int): Message = messages.find { it.id == msgId } ?: throw MessageNotFoundException(msgId)

    fun getUnreadChatsCount(): Int =  messages.asSequence().filter { !it.isRead }.map { it.chatId }.toSet().count()

    fun getChats(): String = "Список чатов:\n${chats.asSequence().mapIndexed { index, chat -> "${index + 1}: ${chat.title}" }.joinToString(separator = "\n")}"

    fun getLastMessageFromChats(): String = "Последние сообщения чатов:\n" +
            chats.asSequence().mapIndexed { index, chat -> "${index + 1}: ${chat.title} - ${getLastMessage(chat.id).text}" }.joinToString(separator = "\n")

    fun getMessagesByChat(chatId: Int): String = "Список сообщений для чата ${getById(chatId).title}:\n" +
            messages.asSequence().filter { it.chatId == chatId }.sortedBy { it.date }.mapIndexed { index, msg -> "${index + 1}: ${msg.text}" }.joinToString(separator = "\n")

    fun getLastMessage(chatId: Int): Message = messages.asSequence().filter { it.chatId == chatId }.sortedBy { it.date }.last()

    fun clear() {
        chats.clear()
        lastId = 0
    }

    fun clearMessages() {
        messages.clear()
        lastIdMessage = 0
    }
}