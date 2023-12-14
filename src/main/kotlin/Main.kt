fun main(args: Array<String>) {
    val chats = listOf(
        DirectChat(1, 1, "Test"),
        DirectChat(2, 2, "Test"),
        DirectChat(3, 3, "Test")
    )
    val messages = listOf(
        Message(1, 1, "Test", isRead = true),
        Message(2, 1, "Test", isRead = false),
        Message(3, 2, "Test", isRead = false),
        Message(4, 3, "Test", isRead = true),
    )
    ChatService.add(DirectChat(1, 2, "Test"), Message(1, 1, "Test", isRead = false))
    ChatService.createMessage(1, Message(4, 1, "Test4", isRead = false))
    ChatService.add(DirectChat(2, 1, "Test2"), Message(2, 2, "Test2", isRead = false))
    ChatService.add(DirectChat(2, 1, "Test3"), Message(3, 3, "Test3", isRead = false))
    println(ChatService.getMessagesByChat(2, 2))

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}